package cn.nsu.edu.web.four.utils;
import java.util.List;

/**
 * Created by 灵魂都在冒香气的神 on 2018/3/23.
 */
public class Page<T>
{
    public static final Integer NUM=10;
    public List<T> subList(List<T> list,Integer page)
    {
         /*
            数据分页
         */
        page=(page<=0)?1:page;   //page小于0的处理
        int size=list.size();
        int endIndex=(size<=NUM)?size:0;   //赛程小于或等于10个
        int fromIndex=0;
        if ((endIndex==0)&&(page*NUM>list.size()))   //赛程大于10个，且page越界
        {
            fromIndex=size-NUM;     //取最后10个赛程
            endIndex=size;
        }
        fromIndex=(endIndex!=0)?fromIndex:(page-1)*NUM;
        endIndex=(endIndex!=0)?endIndex:page*NUM;    //即便赛程小于10个导致前面endIndex=size也不影响逻辑
        List<T> childList=list.subList(fromIndex,endIndex);
        return childList;
    }
}
