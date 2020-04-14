// IClientInterface.aidl
package com.yoyozhangh.github.ipc;

// Declare any non-default types here with import statements

interface IClientInterface {
  void callBack(String requestKey,String resultStr);
}
