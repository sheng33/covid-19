//index.js
//获取应用实例
const app = getApp()

Page({
  data: {
    motto: 'Hello World',
    netStatus:true,
    menuList:[],
    "imgUrl":""
  },
  onLoad: function () {
    var that = this
    wx.request({
      url: 'http://192.168.211.8:8080/client/authorization',
      data:{
        userid:wx.getStorageSync('userid'),
      },
      method:"POST",
      success(result){
        if(result.data.code == -1){
          that.popup = that.selectComponent("#popup");
          that.popup.showPopup();
        }
        console.log(result)
      }
    }),
    wx.request({
      url: 'http://localhost:8080/clientsysConfig/getBanner',
      data:{
        bannerId:1
      },
      method:"GET",
      success(result){
        that.setData({
          imgUrl: result.data.data.imgUrl
        })
      }
    }),
    wx.request({
      url: 'http://localhost:8080/clientsysConfig/getMenuList',
      method:'GET',
      success(result){
        that.setData({
          menuList : result.data.data
        })
        console.log(result.data.data)
      }
    })
  },
  //确认事件
  _success() {
    this.popup.hidePopup();
    wx.navigateTo({
      url: '../userinfo/userinfo',
    })
  }
})
