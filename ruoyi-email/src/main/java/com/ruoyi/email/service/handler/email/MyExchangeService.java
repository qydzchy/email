package com.ruoyi.email.service.handler.email;


import com.ruoyi.common.enums.email.ProtocolTypeEnum;
import com.ruoyi.common.exception.mailbox.MailPlusException;
import lombok.extern.slf4j.Slf4j;
import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.WebProxy;
import microsoft.exchange.webservices.data.core.enumeration.property.WellKnownFolderName;
import microsoft.exchange.webservices.data.core.enumeration.search.FolderTraversal;
import microsoft.exchange.webservices.data.core.enumeration.search.SortDirection;
import microsoft.exchange.webservices.data.core.service.folder.Folder;
import microsoft.exchange.webservices.data.core.service.item.EmailMessage;
import microsoft.exchange.webservices.data.core.service.item.Item;
import microsoft.exchange.webservices.data.core.service.schema.FolderSchema;
import microsoft.exchange.webservices.data.core.service.schema.ItemSchema;
import microsoft.exchange.webservices.data.credential.WebCredentials;
import microsoft.exchange.webservices.data.search.FindFoldersResults;
import microsoft.exchange.webservices.data.search.FindItemsResults;
import microsoft.exchange.webservices.data.search.FolderView;
import microsoft.exchange.webservices.data.search.ItemView;
import microsoft.exchange.webservices.data.search.filter.SearchFilter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.*;

@Slf4j
@Component
public class MyExchangeService implements IMailService {

    public MyExchangeService() {
    }

    @Override
    public UniversalMail parseEmail(MailItem mailItem, String localSavePath) throws MailPlusException {
        return MailItemParser.parseMail(mailItem, localSavePath);
    }

    public List<MailItem> listAll(MailConn mailConn, List<String> existUidList) throws MailPlusException {
        int numEmailsToFetch = 300;
        try {
            FindItemsResults items = getItems(mailConn, numEmailsToFetch);
            ArrayList<Item> itemList = items.getItems();
            List<MailItem> mList = Collections.synchronizedList(new ArrayList<>());

            for (Item item : itemList) {
                if (!(item instanceof EmailMessage)) continue;

                EmailMessage message = (EmailMessage) item;
                mList.add(MailItem.builder().exchangeMessage(message).build());
            }

            return mList;
        } catch (Exception var21) {
            var21.printStackTrace();
            throw new MailPlusException(var21.getMessage());
        }
    }

    private FindItemsResults getItems(MailConn mailConn, int numEmailsToFetch) throws Exception {
        ExchangeService exchangeService = mailConn.getExchangeService();
        Folder msgFolderRoot = Folder.bind(exchangeService, WellKnownFolderName.MsgFolderRoot);
        FolderView folderView = new FolderView(1);
        folderView.setTraversal(FolderTraversal.Deep);
        FindFoldersResults folders = msgFolderRoot.findFolders(new SearchFilter.IsEqualTo(FolderSchema.DisplayName, "Inbox"), folderView);

        if (folders.getFolders().size() == 0) {
            throw new MailPlusException("Inbox not found");
        }

        Folder folder = folders.getFolders().get(0);
        ItemView itemView = new ItemView(numEmailsToFetch);
        itemView.getOrderBy().add(ItemSchema.DateTimeReceived, SortDirection.Descending);

        return exchangeService.findItems(folder.getId(), itemView);
    }

    public MailConn createConn(MailConnCfg mailConnCfg, boolean proxy) throws MailPlusException {
        ExchangeService service = new ExchangeService();
        if (proxy) {
            WebProxy webProxy = new WebProxy(mailConnCfg.getProxyHost(), mailConnCfg.getProxyPort().intValue());
            service.setWebProxy(webProxy);
        }

        service.setCredentials(new WebCredentials(mailConnCfg.getEmail(), mailConnCfg.getPassword()));
        service.setTimeout(600000);

        try {
            if (StringUtils.isNotBlank(mailConnCfg.getHost()) && !mailConnCfg.getHost().equals("-")) {
                URI uri = new URI(mailConnCfg.getHost());
                service.setUrl(uri);
            } else {
                service.autodiscoverUrl(mailConnCfg.getEmail(), (redirectionUrl) -> {
                    return redirectionUrl.toLowerCase().startsWith("https://");
                });
            }

            getItems(MailConn.builder().exchangeService(service).build(), 1); // A simple test to ensure connectivity.
            return MailConn.builder().exchangeService(service).build();
        } catch (Exception var5) {
            var5.printStackTrace();
            throw new MailPlusException(var5.getMessage());
        }
    }

    @Override
    public ProtocolTypeEnum getProtocolTypeEnum() {
        return ProtocolTypeEnum.EXCHANGE;
    }
}
