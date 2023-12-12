package com.ruoyi.email;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.customer.mapper.CustomerMapper;
import com.ruoyi.customer.service.ICustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerTest {

    @Resource
    private CustomerMapper customerMapper;
    @Resource
    private ICustomerService customerService;

    @Test
    public void test01() {
       String s = "[{\"label\":\"亚洲(Asia)\",\"value\":\"Asia\",\"type\":\"parent\",\"children\":[{\"value\":\"CN\",\"label\":\"中国(China)\"},{\"value\":\"AF\",\"label\":\"阿富汗(Afghanistan)\"},{\"value\":\"IN\",\"label\":\"印度(India)\"},{\"value\":\"MN\",\"label\":\"蒙古(Mongolia)\"},{\"value\":\"JP\",\"label\":\"日本(Japan)\"},{\"value\":\"SY\",\"label\":\"叙利亚(Syria)\"},{\"value\":\"SA\",\"label\":\"沙特阿拉伯(Saudi Arabia)\"},{\"value\":\"AM\",\"label\":\"亚美尼亚(Armenia)\"},{\"value\":\"BH\",\"label\":\"巴林(Bahrain)\"},{\"value\":\"PK\",\"label\":\"巴基斯坦(Pakistan)\"},{\"value\":\"KP\",\"label\":\"朝鲜(North Korea)\"},{\"value\":\"SG\",\"label\":\"新加坡(Singapore)\"},{\"value\":\"IQ\",\"label\":\"伊拉克(Iraq)\"},{\"value\":\"IL\",\"label\":\"以色列(Israel)\"},{\"value\":\"AZ\",\"label\":\"阿塞拜疆(Azerbaijan)\"},{\"value\":\"MM\",\"label\":\"缅甸(Myanmar)\"},{\"value\":\"BD\",\"label\":\"孟加拉国(Bangladesh)\"},{\"value\":\"KR\",\"label\":\"韩国(South Korea)\"},{\"value\":\"LB\",\"label\":\"黎巴嫩(Lebanon)\"},{\"value\":\"JO\",\"label\":\"约旦(Jordan)\"},{\"value\":\"PS\",\"label\":\"巴勒斯坦(Palestine)\"},{\"value\":\"GE\",\"label\":\"格鲁吉亚(Georgia)\"},{\"value\":\"KW\",\"label\":\"科威特(Kuwait)\"},{\"value\":\"KG\",\"label\":\"吉尔吉斯斯坦(Kyrgyzstan)\"},{\"value\":\"MV\",\"label\":\"马尔代夫(Maldives)\"},{\"value\":\"MY\",\"label\":\"马来西亚(Malaysia)\"},{\"value\":\"YE\",\"label\":\"也门(Yemen)\"},{\"value\":\"BN\",\"label\":\"文莱(Brunei)\"},{\"value\":\"LA\",\"label\":\"老挝(Laos)\"},{\"value\":\"KH\",\"label\":\"柬埔寨(Cambodia)\"},{\"value\":\"QA\",\"label\":\"卡塔尔(Qatar)\"},{\"value\":\"KZ\",\"label\":\"哈萨克斯坦(Kazakhstan)\"},{\"value\":\"NP\",\"label\":\"尼泊尔(Nepal)\"},{\"value\":\"TH\",\"label\":\"泰国(Thailand)\"},{\"value\":\"AE\",\"label\":\"阿联酋(United Arab Emirates)\"},{\"value\":\"BT\",\"label\":\"不丹(Bhutan)\"},{\"value\":\"OM\",\"label\":\"阿曼(Oman)\"},{\"value\":\"TM\",\"label\":\"土库曼斯坦(Turkmenistan)\"},{\"value\":\"RU\",\"label\":\"俄罗斯(Russia)\"},{\"value\":\"LK\",\"label\":\"斯里兰卡(Sri Lanka)\"},{\"value\":\"ID\",\"label\":\"印度尼西亚(Indonesia)\"},{\"value\":\"IR\",\"label\":\"伊朗(Iran)\"},{\"value\":\"TR\",\"label\":\"土耳其(Turkey)\"},{\"value\":\"TJ\",\"label\":\"塔吉克斯坦(Tajikistan)\"},{\"value\":\"CY\",\"label\":\"塞浦路斯(Cyprus)\"},{\"value\":\"TL\",\"label\":\"东帝汶(Timor-Leste)\"},{\"value\":\"UZ\",\"label\":\"乌兹别克斯坦(Uzbekistan)\"},{\"value\":\"VN\",\"label\":\"越南(Vietnam)\"}]},{\"label\":\"北美洲(North America)\",\"value\":\"North America\",\"type\":\"parent\",\"children\":[{\"value\":\"US\",\"label\":\"美国(United States)\"},{\"value\":\"CA\",\"label\":\"加拿大(Canada)\"},{\"value\":\"MX\",\"label\":\"墨西哥(Mexico)\"},{\"value\":\"GT\",\"label\":\"危地马拉(Guatemala)\"},{\"value\":\"BZ\",\"label\":\"伯利兹(Belize)\"},{\"value\":\"SV\",\"label\":\"萨尔瓦多(El Salvador)\"},{\"value\":\"HN\",\"label\":\"洪都拉斯(Honduras)\"},{\"value\":\"PA\",\"label\":\"巴拿马(Panama)\"},{\"value\":\"BS\",\"label\":\"巴哈马(Bahamas)\"},{\"value\":\"CU\",\"label\":\"古巴(Cuba)\"},{\"value\":\"JM\",\"label\":\"牙买加(Jamaica)\"},{\"value\":\"HT\",\"label\":\"海地(Haiti)\"},{\"value\":\"DO\",\"label\":\"多米尼加(Dominican Republic)\"},{\"value\":\"CR\",\"label\":\"哥斯达黎加(Costa Rica)\"},{\"value\":\"KN\",\"label\":\"圣基茨和尼维斯(Saint Kitts and Nevis)\"},{\"value\":\"AG\",\"label\":\"安提瓜和巴布达(Antigua and Barbuda)\"},{\"value\":\"DM\",\"label\":\"多米尼克(Dominica)\"},{\"value\":\"LC\",\"label\":\"圣卢西亚(Saint Lucia)\"},{\"value\":\"VC\",\"label\":\"圣文森特和格林纳丁斯(Saint Vincent and the Grenadines)\"},{\"value\":\"BB\",\"label\":\"巴巴多斯(Barbados)\"},{\"value\":\"GD\",\"label\":\"格林纳达(Grenada)\"},{\"value\":\"TT\",\"label\":\"特立尼达和多巴哥(Trinidad and Tobago)\"},{\"value\":\"NI\",\"label\":\"尼加拉瓜(Nicaragua)\"}]},{\"label\":\"南美洲(South America)\",\"value\":\"South America\",\"type\":\"parent\",\"children\":[{\"value\":\"AR\",\"label\":\"阿根廷(Argentina)\"},{\"value\":\"BO\",\"label\":\"玻利维亚(Bolivia)\"},{\"value\":\"BR\",\"label\":\"巴西(Brazil)\"},{\"value\":\"CL\",\"label\":\"智利(Chile)\"},{\"value\":\"CO\",\"label\":\"哥伦比亚(Colombia)\"},{\"value\":\"EC\",\"label\":\"厄瓜多尔(Ecuador)\"},{\"value\":\"GY\",\"label\":\"圭亚那(Guyana)\"},{\"value\":\"PE\",\"label\":\"秘鲁(Peru)\"},{\"value\":\"PY\",\"label\":\"巴拉圭(Paraguay)\"},{\"value\":\"SR\",\"label\":\"苏里南(Suriname)\"},{\"value\":\"UY\",\"label\":\"乌拉圭(Uruguay)\"},{\"value\":\"VE\",\"label\":\"委内瑞拉(Venezuela)\"}]},{\"label\":\"欧洲(Europe)\",\"value\":\"Europe\",\"type\":\"parent\",\"children\":[{\"value\":\"AL\",\"label\":\"阿尔巴尼亚(Albania)\"},{\"value\":\"AD\",\"label\":\"安道尔(Andorra)\"},{\"value\":\"AT\",\"label\":\"奥地利(Austria)\"},{\"value\":\"BY\",\"label\":\"白俄罗斯(Belarus)\"},{\"value\":\"BE\",\"label\":\"比利时(Belgium)\"},{\"value\":\"BA\",\"label\":\"波斯尼亚和黑塞哥维那(Bosnia and Herzegovina)\"},{\"value\":\"BG\",\"label\":\"保加利亚(Bulgaria)\"},{\"value\":\"HR\",\"label\":\"克罗地亚(Croatia)\"},{\"value\":\"CY\",\"label\":\"塞浦路斯(Cyprus)\"},{\"value\":\"CZ\",\"label\":\"捷克(Czechia)\"},{\"value\":\"DK\",\"label\":\"丹麦(Denmark)\"},{\"value\":\"EE\",\"label\":\"爱沙尼亚(Estonia)\"},{\"value\":\"FI\",\"label\":\"芬兰(Finland)\"},{\"value\":\"FR\",\"label\":\"法国(France)\"},{\"value\":\"DE\",\"label\":\"德国(Germany)\"},{\"value\":\"GR\",\"label\":\"希腊(Greece)\"},{\"value\":\"HU\",\"label\":\"匈牙利(Hungary)\"},{\"value\":\"IS\",\"label\":\"冰岛(Iceland)\"},{\"value\":\"IE\",\"label\":\"爱尔兰(Ireland)\"},{\"value\":\"IT\",\"label\":\"意大利(Italy)\"},{\"value\":\"LV\",\"label\":\"拉脱维亚(Latvia)\"},{\"value\":\"LI\",\"label\":\"列支敦士登(Liechtenstein)\"},{\"value\":\"LTA\",\"label\":\"立陶宛(Lithuania)\"},{\"value\":\"LU\",\"label\":\"卢森堡(Luxembourg)\"},{\"value\":\"MT\",\"label\":\"马耳他(Malta)\"},{\"value\":\"MD\",\"label\":\"摩尔多瓦(Moldova)\"},{\"value\":\"MC\",\"label\":\"摩纳哥(Monaco)\"},{\"value\":\"ME\",\"label\":\"黑山(Montenegro)\"},{\"value\":\"NL\",\"label\":\"荷兰(Netherlands)\"},{\"value\":\"MK\",\"label\":\"北马其顿(North Macedonia)\"},{\"value\":\"NO\",\"label\":\"挪威(Norway)\"},{\"value\":\"PL\",\"label\":\"波兰(Poland)\"},{\"value\":\"PT\",\"label\":\"葡萄牙(Portugal)\"},{\"value\":\"RO\",\"label\":\"罗马尼亚(Romania)\"},{\"value\":\"RU\",\"label\":\"俄罗斯(Russia)\"},{\"value\":\"SM\",\"label\":\"圣马力诺(San Marino)\"},{\"value\":\"RS\",\"label\":\"塞尔维亚(Serbia)\"},{\"value\":\"SK\",\"label\":\"斯洛伐克(Slovakia)\"},{\"value\":\"SI\",\"label\":\"斯洛文尼亚(Slovenia)\"},{\"value\":\"ES\",\"label\":\"西班牙(Spain)\"},{\"value\":\"SE\",\"label\":\"瑞典(Sweden)\"},{\"value\":\"CH\",\"label\":\"瑞士(Switzerland)\"},{\"value\":\"TR\",\"label\":\"土耳其(Turkey)\"},{\"value\":\"UA\",\"label\":\"乌克兰(Ukraine)\"},{\"value\":\"GB\",\"label\":\"英国(United Kingdom)\"},{\"value\":\"VA\",\"label\":\"梵蒂冈(Vatican City)\"}]},{\"label\":\"非洲(Africa)\",\"value\":\"Africa\",\"type\":\"parent\",\"children\":[{\"value\":\"EG\",\"label\":\"埃及(Egypt)\"},{\"value\":\"LY\",\"label\":\"利比亚(Libya)\"},{\"value\":\"TN\",\"label\":\"突尼斯(Tunisia)\"},{\"value\":\"DZ\",\"label\":\"阿尔及利亚(Algeria)\"},{\"value\":\"MA\",\"label\":\"摩洛哥(Morocco)\"},{\"value\":\"ET\",\"label\":\"埃塞俄比亚(Ethiopia)\"},{\"value\":\"ER\",\"label\":\"厄立特里亚(Eritrea)\"},{\"value\":\"SO\",\"label\":\"索马里(Somalia)\"},{\"value\":\"DJ\",\"label\":\"吉布提(Djibouti)\"},{\"value\":\"KE\",\"label\":\"肯尼亚(Kenya)\"},{\"value\":\"TZ\",\"label\":\"坦桑尼亚(Tanzania)\"},{\"value\":\"UG\",\"label\":\"乌干达(Uganda)\"},{\"value\":\"RW\",\"label\":\"卢旺达(Rwanda)\"},{\"value\":\"BI\",\"label\":\"布隆迪(Burundi)\"},{\"value\":\"SC\",\"label\":\"塞舌尔(Seychelles)\"},{\"value\":\"SD\",\"label\":\"苏丹(Sudan)\"},{\"value\":\"SS\",\"label\":\"南苏丹(South Sudan)\"},{\"value\":\"TD\",\"label\":\"乍得(Chad)\"},{\"value\":\"CF\",\"label\":\"中非(Central African Republic)\"},{\"value\":\"CM\",\"label\":\"喀麦隆(Cameroon)\"},{\"value\":\"GQ\",\"label\":\"赤道几内亚(Equatorial Guinea)\"},{\"value\":\"GA\",\"label\":\"加蓬(Gabon)\"},{\"value\":\"CG\",\"label\":\"刚果共和国(Congo Republic)\"},{\"value\":\"CD\",\"label\":\"刚果民主共和国(Congo Democratic Republic)\"},{\"value\":\"ST\",\"label\":\"圣多美和普林西比(Sao Tome and Principe)\"},{\"value\":\"MR\",\"label\":\"毛里塔尼亚(Mauritania)\"},{\"value\":\"SN\",\"label\":\"塞内加尔(Senegal)\"},{\"value\":\"GM\",\"label\":\"冈比亚(Gambia)\"},{\"value\":\"ML\",\"label\":\"马里(Mali)\"},{\"value\":\"BF\",\"label\":\"布基纳法索(Burkina Faso)\"},{\"value\":\"GN\",\"label\":\"几内亚(Guinea)\"},{\"value\":\"GW\",\"label\":\"几内亚比绍(Guinea-Bissau)\"},{\"value\":\"CV\",\"label\":\"佛得角(Cape Verde)\"},{\"value\":\"SL\",\"label\":\"塞拉利昂(Sierra Leone)\"},{\"value\":\"LR\",\"label\":\"利比里亚(Liberia)\"},{\"value\":\"CI\",\"label\":\"科特迪瓦(Cote d\\\"Ivoire)\"},{\"value\":\"GH\",\"label\":\"加纳(Ghana)\"},{\"value\":\"TG\",\"label\":\"多哥(Togo)\"},{\"value\":\"BJ\",\"label\":\"贝宁(Benin)\"},{\"value\":\"NE\",\"label\":\"尼日尔(Niger)\"},{\"value\":\"NG\",\"label\":\"尼日利亚(Nigeria)\"},{\"value\":\"ZM\",\"label\":\"赞比亚(Zambia)\"},{\"value\":\"AO\",\"label\":\"安哥拉(Angola)\"},{\"value\":\"ZW\",\"label\":\"津巴布韦(Zimbabwe)\"},{\"value\":\"MW\",\"label\":\"马拉维(Malawi)\"},{\"value\":\"MZ\",\"label\":\"莫桑比克(Mozambique)\"},{\"value\":\"BW\",\"label\":\"博茨瓦纳(Botswana)\"},{\"value\":\"NA\",\"label\":\"纳米比亚(Namibia)\"},{\"value\":\"ZA\",\"label\":\"南非(South Africa)\"},{\"value\":\"SZ\",\"label\":\"斯威士兰(Eswatini)\"},{\"value\":\"LS\",\"label\":\"莱索托(Lesotho)\"},{\"value\":\"MG\",\"label\":\"马达加斯加(Madagascar)\"},{\"value\":\"KM\",\"label\":\"科摩罗(Comoros)\"},{\"value\":\"MU\",\"label\":\"毛里求斯(Mauritius)\"}]},{\"label\":\"大洋洲(Oceania)\",\"value\":\"Oceania\",\"type\":\"parent\",\"children\":[{\"value\":\"AU\",\"label\":\"澳大利亚(Australia)\"},{\"value\":\"NZ\",\"label\":\"新西兰(New Zealand)\"},{\"value\":\"PW\",\"label\":\"帕劳(Palau)\"},{\"value\":\"FM\",\"label\":\"密克罗尼西亚联邦(Federated States of Micronesia)\"},{\"value\":\"MH\",\"label\":\"马绍尔群岛(Marshall Islands)\"},{\"value\":\"NR\",\"label\":\"瑙鲁(Nauru)\"},{\"value\":\"PG\",\"label\":\"巴布亚新几内亚(Papua New Guinea)\"},{\"value\":\"SB\",\"label\":\"所罗门群岛(Solomon Islands)\"},{\"value\":\"VU\",\"label\":\"瓦努阿图(Vanuatu)\"},{\"value\":\"TV\",\"label\":\"图瓦卢(Tuvalu)\"},{\"value\":\"FJ\",\"label\":\"斐济(Fiji)\"},{\"value\":\"WS\",\"label\":\"萨摩亚(Samoa)\"},{\"value\":\"KI\",\"label\":\"基里巴斯(Kiribati)\"},{\"value\":\"TO\",\"label\":\"汤加(Tonga)\"},{\"value\":\"CK\",\"label\":\"库克群岛(Cook Islands)\"},{\"value\":\"NU\",\"label\":\"纽埃(Niue)\"}]},{\"label\":\"南极洲(Antarctica)\",\"value\":\"Antarctica\",\"type\":\"parent\",\"children\":[{\"value\":\"AQ\",\"label\":\"南极洲(Antarctica)\"},{\"value\":\"BI\",\"label\":\"布韦岛(Bouvet Island)\"},{\"value\":\"FS\",\"label\":\"法属南部领地(French Southern Territories)\"},{\"value\":\"HI\",\"label\":\"赫德岛和麦克唐纳群岛(Heard Island and McDonald Islands)\"}]}]";

        JSONArray jsonArray = JSON.parseArray(s);
        for (Object jsonObject : jsonArray) {
            JSONObject json = (JSONObject) jsonObject;
            String type = json.getString("value");
            JSONArray children = json.getJSONArray("children");

            for (Object child : children) {
                JSONObject childJson = (JSONObject) child;
                String value = childJson.getString("value");
                String label = childJson.getString("label");
                System.out.println("INSERT INTO `customer_country`(`type`, `value`, `label`) VALUES ('" + type + "', '" + value + "', '" + label + "');");
                customerMapper.insertBaseCountry(type, value, label);
            }
        }
    }

    @Test
    public void test02() {
        Map<Integer, Integer> ratingMapList = customerService.selectCustomerCountGroupByRating(1L);

        System.out.println(ratingMapList);
    }
}
