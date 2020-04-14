// IServerlInterface.aidl
package com.yoyozhangh.github.ipc;

// Declare any non-default types here with import statements
import com.yoyozhangh.github.ipc.IClientInterface;


interface IServerlInterface {
   void excuteAsync(String requestKey,String requestParams);

   String excuteSync(String requestKey,String requestParams);

   void registerCallBack(IClientInterface iClientInterface);
}
