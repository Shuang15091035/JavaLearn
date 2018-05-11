package com.jpym.ymfrontgm.controller;

import com.jpym.ymfrontgm.exception.YmfrontGmException;
import com.jpym.ymfrontgm.model.*;
import com.jpym.ymfrontgm.service.*;
import com.jpym.ymfrontgm.util.MsgConstant;
import com.jpym.ymfrontgm.util.Result;
import com.jpym.ymfrontgm.util.ResultUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class ThirdDataQueryController {

    @Autowired
    private FundManagerService fundManagerService;
    @Autowired
    private FundBonusService fundBonusService;
    @Autowired
    private FundCompanyService fundCompanyService;
    @Autowired
    private FundDocumentService fundDocumentService;
    @Autowired
    private FundHistoryNavService fundHistoryNavService;
    @Autowired
    private FundIncomeFigureService fundIncomeFigureService;
    @Autowired
    private FundPerformanceService fundPerformanceService;
    @Autowired
    private FundStrategyService fundStrategyService;
    @Autowired
    private GMFundListConfigureService gmFundListConfigureService;
    @Autowired
    private FundNoticeService fundNoticeService;

    /**
     * 查询基金分红列表
     *
     * @param fundcode 基金代码
     */
    @GetMapping( value = "/searchFundBonus" )
    public Result searchFundBonus(@RequestParam( "fundcode" ) String fundcode,
                                  @RequestParam( value = "pageindex", defaultValue = "1" ) Number pageindex,
                                  @RequestParam( value = "pagesize", defaultValue = MsgConstant.PAGE_SIZE ) Number pagesize) throws Exception {
        Map pageMap = new HashMap();
        try {
            List<FundBonusModel> fundBonus = fundBonusService.searchFundBonus(fundcode, getfromIndex(pageindex, pagesize), getToIndex(pageindex, pagesize));
            pageMap = addTotalPageAndRemoveField(JSONArray.fromObject(fundBonus), "nums", pagesize.intValue());
            return ResultUtil.success(MsgConstant.SUCCESS_CODE, "基金分红列表成功", fundBonus == null ? Collections.emptyList() : pageMap);
        } catch (BadSqlGrammarException sqlException) {
            throw new YmfrontGmException(MsgConstant.ERROR_CODE, "bad SQL grammar");
        }

        // TODO: 2018/2/1  基金分红列表 完成待测试
    }

    /**
     * 查询基金经理信息
     *
     * @param fundcode 基金代码
     */
    @GetMapping( value = "/fundManagerInformation" )
    public Result fundManagerInformation(@RequestParam( "fundcode" ) String fundcode) throws Exception {
        try {
            List<FundManagerModel> managers = fundManagerService.fundManagerInformation(fundcode);
            return ResultUtil.success(MsgConstant.SUCCESS_CODE, "基金经理信息成功", managers == null ? Collections.emptyList() : managers);
        } catch (BadSqlGrammarException sqlException) {
            throw new YmfrontGmException(MsgConstant.ERROR_CODE, "bad SQL grammar");
        }

        // TODO: 2018/2/1  基金经理信息 完成待测试
    }


    /**
     * 查询基金公司
     *
     * @param fundcode 基金代码
     */
    @GetMapping( value = "/searchFundCompany" )
    public Result searchFundCompany(@RequestParam( "fundcode" ) String fundcode) throws Exception {
        try {
            FundCompanyModel fundCompany = fundCompanyService.searchFundCompany(fundcode);
            return ResultUtil.success(MsgConstant.SUCCESS_CODE, "基金公司信息成功", fundCompany == null ? Collections.emptyMap() : fundCompany);
        } catch (BadSqlGrammarException sqlException) {
            throw new YmfrontGmException(MsgConstant.ERROR_CODE, "bad SQL grammar");
        }

        // TODO: 2018/2/1  基金公司 完成待测试
    }

    /**
     * 查询基金档案
     *
     * @param fundcode 基金代码
     */
    @GetMapping( value = "/searchFundDocument" )
    public Result searchFundDocument(@RequestParam( "fundcode" ) String fundcode) throws Exception {
        Map<String, Object> fundDocument = new HashMap<>();
        try {
            FundIntroductionModel fundIntroduction = fundDocumentService.searchFundIntroduction(fundcode);
            FundAssetComponentModel fundAssetComponentModel = fundDocumentService.searchFundAssetComponent(fundcode);
            List<TopTenStocksModel> TopTenStocks = fundDocumentService.searchTopTenStocks(fundcode);
            fundDocument.put("fundintroduce", fundIntroduction == null ? Collections.emptyMap() : fundIntroduction);
            fundDocument.put("fundAssetComponent", fundAssetComponentModel == null ? Collections.emptyMap() : fundAssetComponentModel);
            fundDocument.put("TopTenStocks", TopTenStocks);
            return ResultUtil.success(MsgConstant.SUCCESS_CODE, "基金档案信息成功", fundDocument == null ? Collections.emptyMap() : fundDocument);
        } catch (BadSqlGrammarException sqlException) {
            throw new YmfrontGmException(MsgConstant.ERROR_CODE, "bad SQL grammar");
        }
        // TODO: 2018/2/1  基金档案 完成待测试
    }

    /**
     * 查询基金历史净值
     *
     * @param fundcode 基金代码
     */
    @GetMapping( value = "/searchFundHistoryNav" )
    public Result searchFundHistoryNav(@RequestParam( "fundcode" ) String fundcode,
                                       @RequestParam( value = "pageindex", defaultValue = "1" ) Number pageindex,
                                       @RequestParam( value = "pagesize", defaultValue = MsgConstant.PAGE_SIZE ) Number pagesize) throws Exception {
        try {
            Map pageMap = new HashMap();
            List<FundHistoryNavModel> fundHistoryNav = fundHistoryNavService.searchFundHistoryNavByPaging(fundcode, getfromIndex(pageindex, pagesize), getToIndex(pageindex, pagesize));
            pageMap = addTotalPageAndRemoveField(JSONArray.fromObject(fundHistoryNav), "nums", pagesize.intValue());
            return ResultUtil.success(MsgConstant.SUCCESS_CODE, "基金历史净值成功", pageMap == null ? Collections.emptyMap() : pageMap);
        } catch (BadSqlGrammarException sqlException) {
            throw new YmfrontGmException(MsgConstant.ERROR_CODE, "bad SQL grammar");
        }
        // TODO: 2018/2/1  基金历史净值 完成待测试
    }

    /**
     * 基金收益走势
     *
     * @param fundcode 基金代码
     */
    @GetMapping( value = "/searchFundIncomeFigure" )
    public Result searchFundIncomeFigure(@RequestParam( "fundcode" ) String fundcode,
                                         @RequestParam( "startdate" ) String startdate,
                                         @RequestParam( "enddate" ) String enddate) throws Exception {
        try {
            List<FundIncomeFigureModel> fundIncomeFigure = fundIncomeFigureService.searchFundIncomeFigure(fundcode, startdate, enddate);
            return ResultUtil.success(MsgConstant.SUCCESS_CODE, "基金收益走势成功", fundIncomeFigure == null ? Collections.emptyList() : fundIncomeFigure);
        } catch (BadSqlGrammarException sqlException) {
            throw new YmfrontGmException(MsgConstant.ERROR_CODE, "bad SQL grammar");
        }

        // TODO: 2018/2/1  基金收益走势 完成待测试
    }

    /**
     * 查询基金业绩表现
     *
     * @param fundcode 基金代码
     */
    @GetMapping( value = "/searchFundPerformance" )
    public Result searchFundPerformance(@RequestParam( "fundcode" ) String fundcode) throws Exception {
        try {
            FundPerformanceModel fundPerformance = fundPerformanceService.searchFundPerformance(fundcode);
            return ResultUtil.success(MsgConstant.SUCCESS_CODE, "基金业绩表现成功", fundPerformance == null ? Collections.emptyMap() : fundPerformance);
        } catch (BadSqlGrammarException sqlException) {
            throw new YmfrontGmException(MsgConstant.ERROR_CODE, "bad SQL grammar");
        }
        // TODO: 2018/2/1  基金业绩表现 完成待测试
    }

    /**
     * 查询基金策略
     *
     * @param fundcode 基金代码
     */
    @GetMapping( value = "/searchFundStrategy" )
    public Result searchFundStrategy(@RequestParam( "fundcode" ) String fundcode) throws Exception {
        try {
            FundStrategyModel funcStrategy = fundStrategyService.searchFundStrategy(fundcode);
            return ResultUtil.success(MsgConstant.SUCCESS_CODE, "基金策略成功", funcStrategy == null ? Collections.emptyMap() : funcStrategy);
        } catch (BadSqlGrammarException sqlException) {
            throw new YmfrontGmException(MsgConstant.ERROR_CODE, "bad SQL grammar");
        }
        // TODO: 2018/2/1  基金策略 完成待测试
    }

    /**
     * 查询基金公告
     *
     * @param fundcode 基金代码
     */
    @GetMapping( value = "/queryFundNotice" )
    public Result queryFundNotice(@RequestParam( value = "fundcode" ) String fundcode,
                                  @RequestParam( value = "pageindex", defaultValue = "1" ) Number pageindex,
                                  @RequestParam( value = "pagesize", defaultValue = MsgConstant.PAGE_SIZE ) Number pagesize) throws Exception {
        try {
            Map pageMap = new HashMap();
            List<FundNoticeModel> fundNotice = fundNoticeService.queryFundNotice(fundcode, getfromIndex(pageindex, pagesize), getToIndex(pageindex, pagesize));
            pageMap = addTotalPageAndRemoveField(JSONArray.fromObject(fundNotice), "nums", pagesize.intValue());
            return ResultUtil.success(MsgConstant.SUCCESS_CODE, "基金公告", fundNotice == null ? Collections.emptyList() : pageMap);
        } catch (BadSqlGrammarException sqlException) {
            throw new YmfrontGmException(MsgConstant.ERROR_CODE, "bad SQL grammar");
        }
    }

    /**
     * 基金列表对应配置表信息
     */
    @GetMapping( value = "/queryFundListConfigureFile" )
    public List<GMFundListConfigureModel> queryFundListConfigureFile() throws Exception {
        return gmFundListConfigureService.queryFundListConfigureFile();
    }

    private int getfromIndex(Number pageindex, Number pagesize) throws Exception {
        checkPageSizeAndPageIndexIsRight(pageindex, pagesize);
        return (pageindex.intValue() - 1) * pagesize.intValue() + 1;
    }

    private int getToIndex(Number pageindex, Number pagesize) throws Exception {
        checkPageSizeAndPageIndexIsRight(pageindex, pagesize);
        return (pageindex.intValue()) * pagesize.intValue();
    }

    private boolean checkPageSizeAndPageIndexIsRight(Number pageindex, Number pagesize) throws Exception {
        if (pagesize.intValue() <= 0 || pageindex.intValue() <= 0)
            throw new YmfrontGmException(MsgConstant.ERROR_CODE, "[pageSize，pageIndex]为正整数");
        return true;
    }

    private Map addTotalPageAndRemoveField(List<Map<String, Object>> resultSet, String fieldName, int pagesizeGreaterTanZero) throws Exception {
        Map transformResultSet = new HashMap();
        List newResultSet = new ArrayList();
        int totalPage = 0, avaliablePage = Integer.parseInt((resultSet.get(0).get(fieldName)).toString());
        if (avaliablePage / pagesizeGreaterTanZero == 0 || avaliablePage % pagesizeGreaterTanZero != 0) {
            totalPage = avaliablePage / pagesizeGreaterTanZero + 1;
        } else {
            totalPage = avaliablePage / pagesizeGreaterTanZero;
        }
        for (int i = 0; i < resultSet.size(); i++) {
            JSONObject resultObj = JSONObject.fromObject(resultSet.get(i));
            resultObj.remove(fieldName);
            newResultSet.add(resultObj);
        }
        transformResultSet.put("resultset", newResultSet);
        transformResultSet.put("totalpage", totalPage);
        return transformResultSet;
    }
}
