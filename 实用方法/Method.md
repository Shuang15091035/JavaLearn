#  Method


/**
    获取ArrayList<Map<String,Object>> String = fundcode 相同的数据的集合
**/
private void getCommonData() {
if (jzFundAssetSize !=0) {
List<Map<String,Object>> fundProduct = new ArrayList<>();
fundProduct.add(jzFundAsset.getJSONObject(0));
fundcodeMap.put(jzFundAsset.getJSONObject(0).getString("fundcode"),fundProduct);
}
for (int i = 1; i < jzFundAssetSize; i++) {
jzFundCode = jzFundAsset.getJSONObject(i).getString("fundcode");
if (fundcodeMap.keySet().contains(jzFundCode)){
ArrayList fundArr = (ArrayList) fundcodeMap.get(jzFundCode);
fundArr.add(jzFundAsset.getJSONObject(i));
}else {
List<Map<String,Object>> fundProduct = new ArrayList<>();
fundProduct.add(jzFundAsset.getJSONObject(i));
fundcodeMap.put(jzFundAsset.getJSONObject(i).getString("fundcode"),fundProduct);
}
}
List<Map<String,Object>> currentFundCodeArr = null;
for (String key:fundcodeMap.keySet()) {
currentFundCodeArr = fieldMapperService.getNewResults((List)fundcodeMap.get(key), FundAssetModel.class);
fundAsset.add(currentFundCodeArr);
}
}
-----------------------------------------------------------------------------
关于入参字典校验
-----------------------------------------------------------------------------
/**
* 针对GET方法参数字典值校验
*/
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

/**
需要校验的参数名称和枚举类的关联
**/
public static final Map<String, Class> paramDictionary = new HashMap() {{
put("invtp", InvtpDic.class);
put("certificatetype", CertificatetDic.class);
put("sex", SexDic.class);
put("country", CountryDic.class);
put("vocationcode", VocationDic.class);
put("channelid", ChannelidDic.class);
put("authenticateflag", AuthenticationMarksDic.class);
put("isspeclpapertype", QuestionDic.class);
put("buyflag", BuyFlagDic.class);
put("vastredeemflag", VastredeemDic.class);
put("saveplanflag", SaveplanDic.class);
put("investcycle", InvestcycleDic.class);
put("riskwarnflag", RiskWarnFlagDic.class);
put("riskmatching", RiskMatchDic.class);
put("listtype", FundListDic.class);
put("fundtype", FundTypeDic.class);
}};
/**
枚举类定义
**/
public enum CertificatetDic {

CertificatetDic_IDCARD("身份证", "0");

private String name;
private String index;

private CertificatetDic(String name, String index) {
this.name = name;
this.index = index;
}

public static boolean enumValueExistOfCurrent(String index) {
for (CertificatetDic bc : CertificatetDic.values()) {
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


