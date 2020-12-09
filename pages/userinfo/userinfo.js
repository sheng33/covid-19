//index.js
//获取应用实例
const app = getApp()
var interval = null
var urlConfig = (wx.getStorageSync('urlConfig'));

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
    username:"",
    mobile:"",
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
  onLoad: function (options) {
    var that = this
    this.setData({
      auth:options.auth
    })
    var temp = wx.getStorageSync('userid') 
    wx.request({
      url: urlConfig.authorization,
      data:{
        userid:temp
      },
      method:"POST",
      success(res){
        that.setData({
          userinfo:res.data.data
        })
      }
    })
  },
  showPopup() {
    this.setData({ show: true });
  },
  sendValue(that){
    console.log(that)
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
