AppCompatActivity
requestWindowFeature(Window.FEATURE_NO_TITLE);//hide Action Bar

public void onClick(View v) {//toast消息
                Toast.makeText(MainActivity.this, "You clicked Button 1",
                        Toast.LENGTH_SHORT).show();
            }

public void onClick(View v) {//销毁程序
                finish();
            }

public void onClick(View v) {//显式intent切换到另外一个Activity
Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
startActivity(intent);
}

public void onClick(View v) {//隐式调用拨号
Intent intent = new Intent(Intent.ACTION_DIAL);
intent.setData(Uri.parse("tel:10086"));
startActivity(intent);
}

public void onClick(View v) {//向下一个活动传递数据,并打印出来
String data = "Hello SecondActivity";
Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
intent.putExtra("extra_data", data);
startActivity(intent);
}
protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
requestWindowFeature(Window.FEATURE_NO_TITLE);
setContentView(R.layout.second_layout);
Intent intent = getIntent();
String data = intent.getStringExtra("extra_data");
Toast.makeText(SecondActivity.this, data,
                Toast.LENGTH_SHORT).show();
}

public void onClick(View v) {//返回数据给上一个活动
Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
startActivityForResult(intent, 1);
}
Button button2 = (Button) findViewById(R.id.button_2);
button2.setOnClickListener(new OnClickListener() {
@Override
public void onClick(View v) {
Intent intent = new Intent();
intent.putExtra("data_return", "Hello FirstActivity");
setResult(RESULT_OK, intent);
finish();
}
});
@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
switch (requestCode) {
case 1:
if (resultCode == RESULT_OK) {
String returnedData = data.getStringExtra("data_return");
Log.d("FirstActivity", returnedData);
}
break;
default:
}
}

Activity 类中定义了七个回调方法，覆盖了活动生命周期的每一个环节，下面我来一一
介绍下这七个方法。
1. onCreate()
这个方法你已经看到过很多次了，每个活动中我们都重写了这个方法，它会在活动
第一次被创建的时候调用。你应该在这个方法中完成活动的初始化操作，比如说加载布
局、绑定事件等。
2. onStart()
这个方法在活动由不可见变为可见的时候调用。
3. onResume()
这个方法在活动准备好和用户进行交互的时候调用。此时的活动一定位于返回栈的
栈顶，并且处于运行状态。
4. onPause()
这个方法在系统准备去启动或者恢复另一个活动的时候调用。我们通常会在这个方
法中将一些消耗CPU 的资源释放掉，以及保存一些关键数据，但这个方法的执行速度
一定要快，不然会影响到新的栈顶活动的使用。
5. onStop()
这个方法在活动完全不可见的时候调用。它和onPause()方法的主要区别在于，如
果启动的新活动是一个对话框式的活动，那么onPause()方法会得到执行，而onStop()
方法并不会执行。
6. onDestroy()
这个方法在活动被销毁之前调用，之后活动的状态将变为销毁状态。
7. onRestart()
这个方法在活动由停止状态变为运行状态之前调用，也就是活动被重新启动了。
以上七个方法中除了onRestart()方法，其他都是两两相对的，从而又可以将活动分为三
种生存期。