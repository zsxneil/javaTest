package thread.test;

/**
* 主任务：汇总任务
*/
class TotalTask implements Runnable {
    private TotalService totalService;

    TotalTask(TotalService totalService) {
        this.totalService = totalService;
    }

    public void run() {
        // 读取内存中各省的数据汇总，过程略。
        totalService.count();
        System.out.println("开始全国汇总");
    }

}
