import com.alibaba.dubbo.rpc.RpcContext;
import com.tl.dubbo.DemoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.Future;

/**
 * Created by 滕柳 on 2018/8/12.
 */
public class Consumer  {

    public static void main(String[] args) throws InterruptedException {
        //测试常规服务
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("consumer.xml");
        context.start();

        //region   dubbo基本测试
        System.out.println("consumer start");
        DemoService demoService = context.getBean(DemoService.class);
        System.out.println("consumer");
        System.out.println(demoService.getPermissions(1L));
        //endregion

        long beginTime = System.currentTimeMillis();

        //region dubbo同步调用测试

        for (int count = 0; count < 10; count++) { // 调用10次
            DemoService asyncInvokeService =
                    (DemoService)context.getBean("asyncInvokeService");
            Integer result = asyncInvokeService.getResult(); //wait 返回结果 等待3秒

            Thread.sleep(4000); //模拟本地复杂的逻辑操作，耗时4秒

            Integer localcalcResult = 2;//本地经过4秒处理得到的计算数据是2

            System.out.println(result + localcalcResult);//根据远程调用返回的结果和本地操作的值，得到结果集

        }
        System.out.println("call 10 times,cost time is "
                + (System.currentTimeMillis() - beginTime));

        Thread.sleep(2000000l);

        //endregion


//        消费端添加 配置文件添加async="true" 就是异步调用
          //region 异步调用测试
        for (int count = 0; count < 10; count++) { // 调用10次
            DemoService asyncInvokeService =
                    (DemoService)context.getBean("asyncInvokeService");
            Integer remotingResult = asyncInvokeService.getResult(); //wait 返回结果 不需要等待

            Thread.sleep(4000); //模拟本地复杂的逻辑操作，耗时4秒

            //Future用来返回异步调用 方法的返回值，之后就可以直接用
            Future<Integer> future = RpcContext.getContext().getFuture();
            try {
                remotingResult = future.get();
            } catch (java.util.concurrent.ExecutionException e) {
                e.printStackTrace();

            }
            Integer localcalcResult = 2;// 本地经过4秒处理得到的计算数据是2

            System.out.println(remotingResult + localcalcResult);// 根据远程调用返回的结果和本地操作的值，得到结果集

        }
        System.out.println("call 10 times,cost time is "
                + (System.currentTimeMillis() - beginTime));

        Thread.sleep(2000000);

        //endregion

    }

}
