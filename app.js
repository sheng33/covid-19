//app.js
var urlConfig = require('./urlConfig.js');
var mapId = "7aa0763b1e4301675eeba6daec7f23b4"
App({
  onLaunch: function () {
    // 展示本地存储能力
    wx.setStorageSync('urlConfig',urlConfig);
    wx.setStorageSync('mapId',mapId)
    urlConfig = wx.getStorageSync('urlConfig')
    var logs = wx.getStorageSync('logs') || []
    logs.unshift(Date.now())
    wx.setStorageSync('logs', logs)
    // 登录
    wx.login({
      success: res => {
        // 发送 res.code 到后台换取 openId, sessionKey, unionId
        wx.request({
          url: urlConfig.decode,
          data:{
            code:res.code
          },
          success(result){
            if(result.data.code == 200){
              wx.setStorageSync('userid', result.data.data.userid)
            }
          }
        })
      }
    })
  },
  globalData: {
    userInfo: null,
    userid:null
  }
})