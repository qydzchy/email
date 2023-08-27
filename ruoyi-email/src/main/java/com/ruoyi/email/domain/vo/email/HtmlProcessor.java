package com.ruoyi.email.domain.vo.email;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.owasp.html.HtmlPolicyBuilder;
import org.owasp.html.PolicyFactory;

public class HtmlProcessor {

    public static String sanitizeHTML(String dirtyHtml) {
        PolicyFactory policy = new HtmlPolicyBuilder().toFactory();
        return policy.sanitize(dirtyHtml);
    }

    public static String extractTextFromHTML(String html) {
        Document doc = Jsoup.parse(html);
        return doc.text();
    }

    public static void main(String[] args) {
        String dirtyHtml = "<html>\\n <head></head>\\n <body>\\n  <div> \\n   <div>\\n    产品名称：小天鹅洗衣机；\\n   </div> \\n   <div>\\n    价格：3600\\n   </div> \\n   <div class=\\\"singnaure\\\"> \\n    <div class=\\\"reAll\\\" style=\\\"color:#333333;font-size:14px;margin-bottom:30px;padding:9px 11px;background-color:#f3f3f3;margin-top:30px;word-break: break-all;line-height: 20px;font-family:'PingFangSC-Medium'\\\">\\n     于2023年07月15日 15:56， <span class=\\\"re-green\\\" style=\\\"color:#2d924b\\\">w0r1d_space@sohu.com</span>写道：\\n    </div> \\n    <div> \\n     <div> \\n      <div class=\\\"reply-body\\\" spellcheck=\\\"true\\\"> \\n       <div style=\\\"font-family: Segoe UI; font-size: 16px; color: #000000;\\\"> \\n        <div>\\n         产品名称：格力空调，\\n        </div> \\n        <div>\\n         价格：1000。\\n        </div> \\n        <div>\\n         &nbsp;\\n        </div> \\n        <div>\\n         &nbsp;\\n        </div> \\n       </div> \\n      </div> \\n      <div style=\\\"clear: both; font-size: 0; height: 0px; overflow: hidden;\\\">\\n       &nbsp;\\n      </div> \\n      <div class=\\\"xiaoman_kuai_mail_sign\\\" spellcheck=\\\"true\\\"> \\n       <div>\\n        --------------------------------------------\\n       </div> \\n       <div>\\n        Thanks &amp; Best regards\\n       </div> \\n       <div>\\n        <br> Grace&nbsp; Lin\\n       </div> \\n       <div>\\n        <br> Hitech(HK) International Limited.&nbsp;&nbsp; &nbsp; &nbsp;&nbsp;\\n       </div> \\n       <div>\\n        &nbsp;&nbsp;\\n        <br> WhatsAPP/ WeChat :&nbsp;&nbsp;+86134 2379 0976\\n       </div> \\n       <div>\\n        <br> Skype:&nbsp; tongpu87978520@163.com\\n       </div> \\n       <div>\\n        <br> Email:&nbsp; &nbsp;sales23@allxchips.com&nbsp;\\n       </div> \\n       <div>\\n        <br> <a href=\\\"https://www.componentsindustry.com\\\" rel=\\\"noopener noreferrer\\\" target=\\\"_blank\\\">https://www.componentsindustry.com</a>&nbsp;\\n       </div> \\n      </div> \\n     </div> \\n    </div> \\n    <img id=\\\"t_r_a_c_k_i_m_g_v_4\\\" name=\\\"t_r_a_c_k_i_m_g_v_4\\\" src=\\\"https://2e392af.hopeful-herschel.com/1e8289ed-74b9-4293-943d-fa93078adc8d/t/eZI8Q6oHO5pIGz8hRymAxJsypB62h7t6x6kzlEtfLlMNGwCSs2LHsw%3D%3D.png\\\" style=\\\"height: 1px !important; max-height: 1px !important; max-width: 1px !important; width: 1px !important\\\">\\n   </div> \\n  </div> \\n  <div style=\\\"height:30px;\\\"></div>\\n  <div style=\\\"height:2px;width:298px;border-bottom:solid 2px #e5e5e5\\\"></div>\\n  <div style=\\\"height:20px;\\\"></div>随心邮-在微信里收发邮件，及时省电又安心\\n </body>\\n</html>";

        // Clean the HTML to prevent XSS
        String cleanHtml = sanitizeHTML(dirtyHtml);
        System.out.println("Cleaned HTML: " + cleanHtml);

        // Extract text content from the cleaned HTML
        String textContent = extractTextFromHTML(cleanHtml);
        System.out.println("Text content: " + textContent);
    }
}
