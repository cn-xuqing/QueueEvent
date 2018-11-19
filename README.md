# QueueEvent
## 队列化执行线程和代码块
### 函数说明
1.主要有两个子类，QueueEventThread用来顺序执行线程，QueueEventCode用来顺序执行代码<br>
2.可以使用setSpeed()设置执行速率<br>
3.enqueue()加入队列<br>
4.next()执行队列中的下一个事件<br>
5.setSnyc()是否同步执行<br>
6.finish()停止队列<br>
### 回调说明
1.回调只有QueueEventCode对象存在<br>
2.onDoingCode()回调，是执行代码块的回调。<br>
3.onDoneCode()回调，是代码执行完毕的回调。<br>
### 举例
```
public void getData1(){
        QueueEventCode.getInstance().enqueue(new OnCodeEvent() {
            @Override
            protected void onDoingCode(Bundle b) {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder().get().url("http://www.baidu.com/").build();
                Call call1 = client.newCall(request);
                //查询省
                call1.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        //失败
                    }

                    @Override
                    public void onResponse(Call call, final Response response) throws IOException {
                        final String responseStr = response.body().string();
                        //成功
                        //查询到省
                        Bundle bundle=new Bundle();
                        bundle.putString("key",responseStr);
                        QueueEventCode.getInstance().next(bundle);//通知下次请求开始，并传递参数
                    }
                });
            }
        }).enqueue(new OnCodeEvent() {
            @Override
            protected void onDoingCode(Bundle b) {//可以从bundle中取出上一次回调来的参数，上一次返回的是省
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder().get().url("http://www.baidu.com/").build();
                Call call1 = client.newCall(request);
                //查询市
                call1.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        //失败
                    }

                    @Override
                    public void onResponse(Call call, final Response response) throws IOException {
                        final String responseStr = response.body().string();
                        //成功
                        //查询到市
                        Bundle bundle=new Bundle();
                        bundle.putString("key",responseStr);
                        QueueEventCode.getInstance().next(bundle);//通知下次请求开始，并传递参数
                    }
                });
            }
        }).enqueue(new OnCodeEvent() {
            @Override
            protected void onDoingCode(Bundle b) {//可以从bundle中取出上一次回调来的参数，上一次返回的是市
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder().get().url("http://www.baidu.com/").build();
                Call call1 = client.newCall(request);
                //查询市
                call1.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        //失败
                    }

                    @Override
                    public void onResponse(Call call, final Response response) throws IOException {
                        final String responseStr = response.body().string();
                        //成功
                        //查询到县
                        Bundle bundle=new Bundle();
                        bundle.putString("key",responseStr);
                        QueueEventCode.getInstance().next(bundle);//通知下次请求开始，并传递参数
                    }
                });
            }
        });
    }
```
