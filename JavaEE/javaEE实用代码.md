# JavaLearn (javaEE)
## Java枚举值定义
public enum ChannelidDic {

CertificatetDic_CONSTRUCTIONBANK("建设银行", "F000"),
CertificatetDic_INDUSTRIALBANK("工业银行", "F001"),
//    CertificatetDic_AGRICULTURABANK("农业银行", "F002"),
CertificatetDic_CHINABANK("中国银行", "F003"),
CertificatetDic_SOCIETEGENERALE("兴业银行", "F004"),
CertificatetDic_EVERBRIGHTBANK("光大银行", "F005"),
//    CertificatetDic_CHINACITICBANK("中信银行", "F006"),
CertificatetDic_PUDONGDEVELOPMENTBANK("上海浦东发展银行", "F007"),
CertificatetDic_BANKOFCOMMUNICATIONS("交通银行", "F008"),
//    CertificatetDic_POSTALSAVINGSBANK("中国邮政储蓄银行", "F009"),
CertificatetDic_PINGANBANK("平安银行", "F010"),
CertificatetDic_SHANGHAIBANK("上海银行", "F011"),
//    CertificatetDic_BEIJINGBANK("北京银行", "F012"),
CertificatetDic_MERCHANTSBANK("招商银行", "F013"),
CertificatetDic_GUANGDONGDEVELOPMENTBANK("广发银行", "F014"),
CertificatetDic_HUAXIABANK("华夏银行", "F016");

private String name;
private String index;

private ChannelidDic(String name, String index) {
this.name = name;
this.index = index;
}

public static String getName(String index) {
for (ChannelidDic bc : ChannelidDic.values()) {
if (bc.index.equals(index)) {
return bc.name;
}
}
return null;
}

public static String getIndex(String name) {
for (ChannelidDic bc : ChannelidDic.values()) {
if (bc.name.equals(name)) {
return bc.index;
}
}
return null;
}

public static boolean enumValueExistOfCurrent(String index) {
for (ChannelidDic bc : ChannelidDic.values()) {
if (bc.index.equals(index)) {
return true;
}
}
return false;
}

public String getName() {
return name;
}

public String getIndex() {
return index;
}

}

## 关于动态监测入参枚举值匹配
public static boolean requestParameterDicCheck(Map requestMap) throws Exception {
Set<String> intersection = new HashSet(); //交集
intersection.clear();
intersection.addAll(MsgConstant.paramDictionary.keySet());
intersection.retainAll(requestMap.keySet());
for (String paramName : intersection) {
String paramValue = requestMap.get(paramName).toString();
if (StringUtil.notEmpty(paramValue)) {
Class enumClass = MsgConstant.paramDictionary.get(paramName);
Method method = enumClass.getMethod("enumValueExistOfCurrent", String.class);
Object result = method.invoke(null, paramValue);
if (!(boolean) result) {
throw new YmfrontGmException(MsgConstant.ERROR_CODE, "[" + paramName + "]" + "输入正确的字典值");
}
}
}
return true;
}

/**
* 针对POST方法参数字典值校验
* POST方法导致请求体被拦截
* if (request.getMethod().equals("POST")){
* BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
* JSONObject name = JSONObject.fromObject(IOUtils.read(reader));
* }
*/
public static boolean requestParameterDicCheck(HttpServletRequest request) throws Exception {
Set<String> intersection = new HashSet(); //交集
intersection.clear();
intersection.addAll(MsgConstant.paramDictionary.keySet());
intersection.retainAll(request.getParameterMap().keySet());
for (String paramName : intersection) {
String paramValue = request.getParameter(paramName);
if (StringUtil.notEmpty(paramValue)) {
Class enumClass = MsgConstant.paramDictionary.get(paramName);
Method method = enumClass.getMethod("enumValueExistOfCurrent", String.class);
Object result = method.invoke(null, paramValue);
if (!(boolean) result) {
throw new YmfrontGmException(MsgConstant.ERROR_CODE, "[" + paramName + "]" + "输入正确的字典值");
}
}
}
return true;
}
## 通过枚举空值入参参数内容
### 1.枚举定义
public enum  QueryRequestParam{
FUNDCODE("基金代码", ""),
FUNDTYPE("基金类型", ""),
FUNDNAME("基金名称", ""),
PAGEINDEX("请求页码", "1"),
PAGESIZE("每页大小", MsgConstant.PAGE_SIZE);

private QueryRequestParam(String name, String defaultValue) {
this.desc = name;
this.defaultValue = defaultValue;
}

public String getDefaultValue() {
return defaultValue;
}

public void setDefaultValue(String defaultValue) {
this.defaultValue = defaultValue;
}

public String getDesc() {
return desc;
}

public void setDesc(String desc) {
this.desc = desc;
}

private String desc;
private String defaultValue;

public static RequestParamMap buildBydefault( ){
RequestParamMap queryRequestParam = new RequestParamMap();
for (QueryRequestParam bc : QueryRequestParam.values()) {
queryRequestParam.put(bc.name().toLowerCase().toString(),bc.defaultValue);
}
return queryRequestParam;
}
}
### 2.枚举参数转化为入参值
public class RequestParamMap extends HashMap {

public final <T extends Enum<T>> void set(Enum<T> key, String value){
this.put(schemakey(key),value);
}
public final <T extends Enum<T>> String get(Enum<T> key){
return StringUtil.empty(schemakey(key))?"":key.name().toLowerCase();
}
private final <T extends Enum<T>> String schemakey(Enum<T> key){
return key.name().toLowerCase();
}
}
### 3.测试调用监测
@Test
public void contextLoads() {
RequestParamMap queryResult = QueryRequestParam.buildBydefault();
queryResult.set(QueryRequestParam.FUNDCODE,"code");
queryResult.set(QueryRequestParam.FUNDNAME,"name");
queryResult.set(QueryRequestParam.FUNDTYPE,"type");
queryResult.set(QueryRequestParam.PAGEINDEX,"index");
int pause = 0;
}
