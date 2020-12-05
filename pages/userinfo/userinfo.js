//index.js
//获取应用实例
const app = getApp()
var interval = null
Page({
  data: {
    title:"信息绑定",
    wxname:"test",
    username:"sheng",
    phone:"15297987428",
    smsCode:"1234",
    phoneMsg:"",
    show: false,
    smsflag:false,
    disabled:false,
    currentTime:60,
    showCountDown:false,
    smsTitle:"发送验证码",
    flag: false
    // userNickName:""
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
  onLoad: function () {
    
  },
  showPopup() {
    console.log("点击true")
    this.setData({ show: true });
  },
  onChangeName(event) {
    // event.detail 为当前输入的值
    this.setData({flag:false})
  },
  onChangePhone(event){
    this.setData({phoneMsg:""})
  },
  onChangeSms(event){
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
    if (this.data.phone == ""){
      this.setData({phoneMsg:"手机号不能为空"})
      pd = false
    }else if(this.data.phone.length!=11){
      this.setData({phoneMsg:"手机号格式错误"})
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
    wx.request({
      url: "http://192.168.211.8:8080/client/userBind",
      data:{
        userid:wx.getStorageSync('userid'),
        username:this.data.username,
        phone:this.data.phone,
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
