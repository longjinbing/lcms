package com.ljb.controller;

import com.ljb.entity.ApiOrder;
import com.ljb.model.OrderModel;
import com.ljb.service.*;
import com.ljb.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/12/17<br>
 * 描述: <br>
 */
@Api(value = "商城模块")
@RestController
@RequestMapping("api/shop")
public class ApiShopController {

    @Autowired
    private ApiGoodsService apiGoodsService;
    @Autowired
    private ApiAddressService apiAddressService;
    @Autowired
    private ApiCartService apiCartService;
    @Autowired
    private ApiGoodsCategoryService apiGoodsCategoryService;
    @Autowired
    private ApiCouponService apiCouponService;
    @Autowired
    private  ApiOrderGoodsService apiOrderGoodsService;
    @Autowired
    private ApiCollectService apiCollectService;
    @Autowired
    private ApiUserCouponService apiUserCouponService;
    @Autowired
    private ApiOrderService apiOrderService;

    @ApiOperation(value = "商品分类列表")
    @GetMapping("/goods/category")
    public R category() {
        return R.ok().put(apiGoodsCategoryService.selectList());
    }

    // 根据体质搜索相关商品
    @ApiOperation(value = "根据关键词搜索商品")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "起始记录数", name = "offset", required = false, paramType = "query", dataType = "int", defaultValue = "0"),
            @ApiImplicitParam(value = "获取记录条数", name = "limit", required = false, paramType = "query", dataType = "int", defaultValue = "10"),
            @ApiImplicitParam(name = "search",value = "关键词",paramType = "query",required = true,dataType = "string")
    })
    @GetMapping("/goods/search")
    public R search(@RequestParam Map<String, Object> params) {
        return R.ok().put(apiGoodsService.selectList(params));
    }

    // 商品列表
    @ApiOperation(value = "根据商品类型ID获取商品列表")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "起始记录数", name = "offset", required = false, paramType = "query", dataType = "int", defaultValue = "0"),
            @ApiImplicitParam(value = "获取记录条数", name = "limit", required = false, paramType = "query", dataType = "int", defaultValue = "10"),
            @ApiImplicitParam(value = "商品分类ID", name = "categoryId", required = true, paramType = "query", dataType = "long"),
    })
    @GetMapping("/goods/list")
    public R list(@RequestParam Map<String, Object> params) {

        return R.ok().put(apiGoodsService.selectList(params));
    }

    // 立即下单
    @ApiOperation(value = "获取用户购物地址信息")
    @GetMapping("/user/address")
    public R orderGoods() {
        return R.ok().put(apiAddressService.addressList());
    }

    // 购物车列表
    @ApiOperation(value = "用户购物车商品信息")
    @GetMapping("/cart")
    public R cartList() {
        return R.ok().put(apiCartService.cartList());
    }

    // 添加到购物车
    @ApiOperation(value = "根据商品ID将商品加入购物车")
    @ApiImplicitParam(name = "id",value = "商品ID",paramType = "path",required = true,dataType = "Long")
    @PostMapping("/cart/add/{id}")
    public R addGoodsToCart(@PathVariable("id") Long id) {
        return R.judge(apiCartService.add(id));
    }

    // 购物车删除商品
    @ApiOperation(value = "根据商品ID将商品从购物车移除")
    @ApiImplicitParam(name = "id",value = "商品ID",paramType = "path",required = true,dataType = "Long")
    @PostMapping("/cart/remove/{id}")
    public R remove(@PathVariable("id") Long id) {
        return R.judge(apiCartService.remove(id));
    }

    @ApiOperation(value = "清空购物车")
    @PostMapping("/cart/remove/all")
    public  R removeAll() {
        return R.judge(apiCartService.removeAll());
    }

    // 购物车减少商品数量
    @ApiOperation(value = "根据商品ID将购物车中对应商品数量加1")
    @ApiImplicitParam(name = "id",value = "商品ID",paramType = "path",required = true,dataType = "Long")
    @PostMapping("/cart/number/add/{id}")
    public R cartAdd(@PathVariable("id") Long id) {
        return R.judge(apiCartService.addNumber(id));
    }

    // 购物车增驾商品数量
    @ApiOperation(value = "根据商品ID将购物车中对应商品数量减1")
    @ApiImplicitParam(name = "id",value = "商品ID",paramType = "path",required = true,dataType = "Long")
    @PostMapping("/cart/number/sub/{id}")
    public R cartSub(@PathVariable("id") Long id) {
        return R.judge(apiCartService.subNumber(id));
    }

    // 提交订单
    @ApiOperation(value = "提交订单")
    @PostMapping("/order")
    public R order(@RequestBody OrderModel orderModel) {
        return R.ok().put(apiOrderService.save(orderModel) );
    }

    // 优惠券列表
    @ApiOperation(value = "商城优惠卷列表")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "起始记录数", name = "offset", required = false, paramType = "query", dataType = "int", defaultValue = "0"),
            @ApiImplicitParam(value = "获取记录条数", name = "limit", required = false, paramType = "query", dataType = "int", defaultValue = "10"),
    })
    @PostMapping("/coupon/list")
    public  R couponList(@RequestParam Map<String, Object> params) {
        return R.ok().put(apiCartService.selectList(params) );
    }

    @ApiOperation(value = "根据优惠卷ID领取优惠卷")
    @ApiImplicitParam(name = "id",value = "优惠卷ID",paramType = "path",required = true,dataType = "Long")
    @PostMapping("/coupon/get/{id}")
    public  R couponAdd(@PathVariable("id") Long id) {
        return R.judge(apiUserCouponService.getCoupon(id));
    }

    @ApiOperation(value = "根据商品ID将商品加入收藏")
    @ApiImplicitParam(name = "id",value = "商品ID",paramType = "path",required = true,dataType = "Long")
    @PostMapping("/collect/add/{id}")
    public R collectAdd(@PathVariable("id") Long id) {
        return R.judge(apiCollectService.add(id));
    }

    // 商品收藏
    @ApiOperation(value = "用户商品收藏列表")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "起始记录数", name = "offset", required = false, paramType = "query", dataType = "int", defaultValue = "0"),
            @ApiImplicitParam(value = "获取记录条数", name = "limit", required = false, paramType = "query", dataType = "int", defaultValue = "10"),
    })
    @GetMapping("/collect/list")
    public R collect(@RequestParam Map<String, Object> params) {
        return R.ok().put(apiCollectService.selectList(params));
    }

    // 收藏夹删除商品
    @ApiOperation(value = "根据商品ID将商品从收藏夹移除")
    @ApiImplicitParam(name = "id",value = "商品ID",paramType = "path",required = true,dataType = "Long")
    @PostMapping("/collect/remove/{id}")
    public R collectRemove(@PathVariable("id") Long id) {
        return R.judge(apiCollectService.remove(id));
    }

    // 根据商品id查找商品ID
    @ApiOperation(value = "根据商品id获取商品详情")
    @ApiImplicitParam(name = "id",value = "商品ID",paramType = "path",required = true,dataType = "Long")
    @GetMapping("/goods/{id}")
    public R goods(@PathVariable("id") Long id) {
        return R.ok().put(apiGoodsService.selectMapById(id));
    }

    @ApiOperation(value = "获取用户订单列表")
    @GetMapping("/order/list")
    public R orderLits(@RequestParam Map<String, Object> params) {
        return R.ok().put(apiOrderService.selectList(params));
    }

    @ApiOperation(value = "根据订单ID获取订单详情")
    @ApiImplicitParam(name = "id",value = "订单ID",paramType = "path",required = true,dataType = "Long")
    @GetMapping("/order/{id}")
    public R orderDetails(@PathVariable Long id) {
        Map<String,Object> map=new HashMap<>();
       ApiOrder apiOrder= apiOrderService.selectById(id);
        map.put("order", apiOrder);
        map.put("goodsList", apiOrderGoodsService.orderGoods(apiOrder.getId()));
        return R.ok().put(map);
    }

}
