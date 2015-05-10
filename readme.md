## Xposed Lua Module

## 说明

Xposed Lua Module 是一个Xposed的模块，他有下面的优点

本模块的优点有如下：

  * 1. 实时加载、实时调试Xposed的功能
  * 2. Lua支持语言，拥有Lua的标准库功能
  * 3. 良好的论坛(bbs.0xcode.org)
  * 4. 几乎支持所有平台(arm,x86,arm-v7)

## 事例

	-- Hook MODEL
	HookStaticObjectField("android.os.Build", "MODEL", "bbbbbbbbbbbbbb")

	-- Hook Andoird IMEI
	HookMethodAfter("android.telephony.TelephonyManager", "getDeviceId", function( param )
		param:setResult("ccccccccccc")
		return true
	end)

## 更多

开发文档: 撰写中...

个人博客: <http://www.sollyu.com/xposed-lua>

论坛地址: <http://bbs.0xcode.org>

