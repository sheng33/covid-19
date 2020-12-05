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
    var urlConfig = (wx.getStorageSync('urlConfig'));
    wx.request({
      url: urlConfig.authorization,
      data:{
        userid:wx.getStorageSync('userid'),
      },
      method:"POST",
      success(result){
        if(result.data.code == -1){
          that.popup = that.selectComponent("#popup");
          that.popup.showPopup();
        }
        if(result.statusCode == 500){
          setTimeout(function(){
            wx.request({
              url: urlConfig.authorization,
              data:{
                userid:wx.getStorageSync('userid'),
              },
              method:"POST",
              success(result){
                if(result.data.code == -1){
                  that.popup = that.selectComponent("#popup");
                  that.popup.showPopup();
                }
              }
            })
          },2000)
        }
        console.log(result)
      },
      fail(res){
        console.log("授权失败")
      }
    }),
    wx.request({
      url: urlConfig.getBanner,
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
      url: urlConfig.getMenuList,
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
