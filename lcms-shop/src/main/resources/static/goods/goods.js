var vm = new Vue(extend(common,{
    data: {
        baseUrl: '/goods/goods',
        statusList: ['已下架', '已上架', '售完'],
        addRules: {
            categoryId: [
            {required: true, message: '分类不能为空'}
        ],
                    goodsSn: [
            {required: true, message: 'SN编码不能为空'}
        ],
                    name: [
            {required: true, message: '名称不能为空'}
        ],
                    brandId: [
            {required: true, message: '品牌不能为空'}
        ],
                    goodsNumber: [
            {required: true, message: '库存量不能为空'}
        ],
                    keywords: [
            {required: true, message: '关键字不能为空'}
        ],
                    goodsBrief: [
            {required: true, message: '好评度不能为空'}
        ],
                    goodsDesc: [
            {required: true, message: '商品描述不能为空'}
        ],
                    addTime: [
            {required: true, message: '上架时间不能为空'}
        ],
                    attributeCategory: [
            {required: true, message: '属性分类不能为空'}
        ],
                    counterPrice: [
            {required: true, message: '专柜价格不能为空'}
        ],
                    extraPrice: [
            {required: true, message: '附加价格不能为空'}
        ],
                    isNew: [
            {required: true, message: '是否上新不能为空'}
        ],
                    goodsUnit: [
            {required: true, message: '商品单位不能为空'}
        ],
                    primaryPicUrl: [
            {required: true, message: '商品主图不能为空'}
        ],
                    listPicUrl: [
            {required: true, message: '商品列表图不能为空'}
        ],
                    retailPrice: [
            {required: true, message: '零售价格不能为空'}
        ],
                    sellVolume: [
            {required: true, message: '销售量不能为空'}
        ],
                    primaryProductId: [
            {required: true, message: '主sku　product_id不能为空'}
        ],
                    unitPrice: [
            {required: true, message: '单位价格，单价不能为空'}
        ],
                    promotionDesc: [
            {required: true, message: '推广信息不能为空'}
        ],
                    promotionTag: [
            {required: true, message: '推广标签不能为空'}
        ],
                    appExclusivePrice: [
            {required: true, message: 'APP专享价不能为空'}
        ],
                    isAppExclusive: [
            {required: true, message: '是否是APP专属不能为空'}
        ],
                    isLimited: [
            {required: true, message: '不能为空'}
        ],
                    isHot: [
            {required: true, message: '不能为空'}
        ],
                    marketPrice: [
            {required: true, message: '不能为空'}
        ],
                    createUserDeptId: [
            {required: true, message: '不能为空'}
        ],
                    status: [
            {required: true, message: '不能为空'}
        ],
                },
        exportFields: [
       {key: 'categoryId', label: '分类'},
       {key: 'goodsSn', label: 'SN编码'},
       {key: 'name', label: '名称'},
       {key: 'brandId', label: '品牌'},
       {key: 'goodsNumber', label: '库存量'},
       {key: 'keywords', label: '关键字'},
       {key: 'goodsBrief', label: '好评度'},
       {key: 'goodsDesc', label: '商品描述'},
       {key: 'addTime', label: '上架时间'},
       {key: 'orderNum', label: '排序'},
       {key: 'attributeCategory', label: '属性分类'},
       {key: 'counterPrice', label: '专柜价格'},
       {key: 'extraPrice', label: '附加价格'},
       {key: 'isNew', label: '是否上新'},
       {key: 'goodsUnit', label: '商品单位'},
       {key: 'primaryPicUrl', label: '商品主图'},
       {key: 'listPicUrl', label: '商品列表图'},
       {key: 'retailPrice', label: '零售价格'},
       {key: 'sellVolume', label: '销售量'},
       {key: 'primaryProductId', label: '主sku　product_id'},
       {key: 'unitPrice', label: '单位价格，单价'},
       {key: 'promotionDesc', label: '推广信息'},
       {key: 'promotionTag', label: '推广标签'},
       {key: 'appExclusivePrice', label: 'APP专享价'},
       {key: 'isAppExclusive', label: '是否是APP专属'},
       {key: 'isLimited', label: ''},
       {key: 'isHot', label: ''},
       {key: 'marketPrice', label: ''},
       {key: 'createUserDeptId', label: ''},
       {key: 'status', label: ''},
       {key: 'updateTime', label: ''},
       {key: 'updateName', label: '操作人'},
        ]
    },
    methods: {

    }
}));
