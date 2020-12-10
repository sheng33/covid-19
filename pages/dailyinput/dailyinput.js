const app = getApp()
var amapFile = require('../../lib/amap-wx.js');
Page({
  data:{
    title:"日常填报",
    userid:wx.getStorageSync('userid'),
    address:"",
    temperature:36.6,
    remark:"",
    istouch:false
  },
  onLoad:function(){
    var that = this;
    var mapId = wx.getStorageSync('mapId')
    var myAmapFun = new amapFile.AMapWX({key:wx.getStorageSync('mapId')});
    myAmapFun.getRegeo({
      success: function(data){
        that.setData({
          address:data[0].regeocodeData.formatted_address
        })
        console.log(data[0].regeocodeData.formatted_address)
        //成功回调
      },
      fail: function(info){
        //失败回调
        
        console.log(info)
      }
    })
  },
  onChange(event) {
    this.setData({
      temperature:event.detail
    })
  },
  onChangeTouch(event) {
    this.setData({
      istouch:!this.data.istouch
    })
  },
  _success(){
    var urlConfig = (wx.getStorageSync('urlConfig'));
    console.log(this.data)
    wx.request({
      url: urlConfig.fillinformation,
      data:{
        userid:this.data.userid,
        address:this.data.address,
        temperature:this.data.temperature,
        remark:this.data.remark,
        istouch:this.data.istouch
      },
      method:"POST",
      success(res){
        console.log(res)
        wx.showToast({
          title: '填报成功',
          icon: "success",
          duration:2000,
          mask:true,
        })
        setTimeout(function(){
          wx.redirectTo({
            url: '../index/index',
          })
        },1500)
      }
    })
  }
})