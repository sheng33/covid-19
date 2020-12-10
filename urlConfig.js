var host = "http://192.168.211.8:8080/";
var config={
  host,
  decode:host+"api/decode",
  authorization:host+"client/authorization",
  getBanner:host+"clientsysConfig/getBanner",
  getMenuList:host+"clientsysConfig/getMenuList",
  userBind:host+"client/userBind",
  fillinformation:host+"client/fillinformation",
  getDailyList:host+"client/getDailyList"
}
module.exports=config;