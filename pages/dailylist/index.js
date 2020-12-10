//index.js
//获取应用实例
const app = getApp()
var urlConfig = wx.getStorageSync('urlConfig')
Page({
  data: {
    username:wx.getStorageSync('userinfo').username,
    list:[],
    loading:false,
    page:1,
    size:10,
  },
  onLoad: function () {
    var that = this
    wx.request({
      url: urlConfig.getDailyList,
      data:{
        page:that.data.page,
        size:that.data.size,
        userid:wx.getStorageSync('userid')
      },
      method:"GET",
      success(res){
        that.setData({
          list:res.data.data.list
        })
      }
    })
  },
  onReachBottom:function(){
    var that = this
    if (this.data.loading) return;  
    this.setData({ loading: true });  
    setTimeout( () =>{
         this.setData({
           loading: false
         })
    }, 2000)
    wx.request({
      url: urlConfig.getDailyList,
      data:{
        page:that.data.page+1,
        size:that.data.size,
        userid:wx.getStorageSync('userid')
      },
      method:"GET",
      success(res){
        that.setData({
          list:that.data.list.concat(res.data.data.list),
          page:that.data.page+1
        })
      }
    })
  }
})
