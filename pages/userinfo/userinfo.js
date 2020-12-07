//index.js
//获取应用实例
const app = getApp()
var interval = null
Page({
  data: {
    title:"信息绑定",
    userinfo:{
      username:"",
      mobile:"",
      isarea:0,
      istemperature:0,
      istouch:0,
    },  
    smsCode:"1234",
    mobileMsg:"",
    show: false,
    smsflag:false,
    disabled:false,
    currentTime:60,
    showCountDown:false,
    smsTitle:"发送验证码",
    flag: false,
    auth:false
  },
  sendSms(){
    var that = this;
    if(!this.data.disabled){
      var currentTime = that.data.currentTime
      interval = setInterval(function () {
        currentTime--;
        that.setData({
          smsTitle: currentTime+'秒'
        })
        if (currentTime <= 0) {
          clearInterval(interval)
          that.setData({
            smsTitle: '重新发送',
            currentTime:60,
            disabled: false   
          })
        }
      }, 1000) 
      that.setData({
        disabled:true
      }) 
    }
  },
  //事件处理函数
  bindViewTap: function() {
    wx.navigateTo({
      url: '../logs/logs'
    })
  },
  onLoad: function (options) {
    this.setData({
      auth:options.auth
    })
    var temp = wx.getStorageSync('userinfo')
    console.log("-----++",temp)
    if(temp.length!=0){
      this.setData({
        userinfo:temp
      })
    }
  },
  showPopup() {
    console.log("点击true")
    this.setData({ show: true });
  },
  onChangeName() {
    // event.detail 为当前输入的值
    this.setData({flag:false})
  },
  onChangemobile(){
    this.setData({mobileMsg:""})
  },
  onChangeSms(){
    this.setData({smsflag:false})
  },
  onClose() {
    console.log("点false")
    this.setData({ show: false });
  },
  
  _success(){
    var pd = true
    if (this.data.username == ""){
      this.setData({flag:true})
      pd = false
    }
    if (this.data.mobile == ""){
      this.setData({mobileMsg:"手机号不能为空"})
      pd = false
    }else if(this.data.mobile.length!=11){
      this.setData({mobileMsg:"手机号格式错误"})
      pd = false
    }
    if (this.data.sms == ""){
      this.setData({smsflag:true})
      pd = false
    }
    if(pd){
      this.submitInfo()
    }
  },
  submitInfo(){
    var urlConfig = (wx.getStorageSync('urlConfig'));
    var that = this
    wx.request({
      url: urlConfig.userBind,
      data:{
        userid:wx.getStorageSync('userid'),
        username:this.data.username,
        mobile:this.data.mobile,
        smsCode:this.data.smsCode
      },
      method:"POST",
      success(result){
        console.log(result)
        if(result.data.code==200){
          wx.showToast({
            title: '绑定成功',
            icon: "success",
            duration:2000,
            mask:true,
          })
          setTimeout(function(){
            wx.redirectTo({
              url: '../index/index',
            })
          },2000)
          console.log("绑定成功")
        }
      }
    })
  }
})
