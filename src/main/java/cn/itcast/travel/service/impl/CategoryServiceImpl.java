package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.CategoryDao;
import cn.itcast.travel.dao.impl.CategoryDaoImpl;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.service.CategoryService;
import cn.itcast.travel.util.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CategoryServiceImpl implements CategoryService {
    private CategoryDao categoryDao=new CategoryDaoImpl();

    @Override
    public List<Category> findAll() {
        //1.从redis中查询
//            //1.1获取jedis客户端
//            Jedis jedis=JedisUtil.getJedis();
//            //1.2可使用sortedset排序查询
////            Set<String> categorys=jedis.zrange("category",0,-1);
//            //1.3查询sortset中的分数（cid）和值（name）
//            Set<Tuple> categorys=jedis.zrangeWithScores("category",0,-1);
//
//            List<Category> cs=null;
//        //2.判断查询的集合是否为空
//        if(categorys==null||categorys.size()==0){
//            System.out.println("从数据库从查询。。。。。");
//            //3.如果为空，需要从数据库查询，将数据存入redis
//            //3.1从数据库查询
//            cs=categoryDao.findAll();
//            //3.2将集合数据存储到redis中的   category的key
//            for(int i=0;i<cs.size();i++){
//                jedis.zadd("category",cs.get(i).getCid(),cs.get(i).getCname());
//            }
//        }else {
//            cs = new ArrayList<Category>();
////            for(String name:categorys){
////                Category category=new Category();
////                category.setCname(name);
////                cs.add(category);
////            }
//
//            for (Tuple tuple : categorys) {
//                Category category = new Category();
//                category.setCname(tuple.getElement());
//                category.setCid((int) tuple.getScore());
//                cs.add(category);
//            }
//        }
//        //3.如果为空，需要从数据库查询，在将数据存入redis
//        //4.如果不为空，直接返回
     return categoryDao.findAll();

    }

//    public static void main(String[] args) {
//        CategoryDao categoryDao=new CategoryDaoImpl();
//     List<Category> lis=   categoryDao.findAll();
//        for (Category e:lis
//             ) {
//            System.out.println(e);
//        }
//    }

}
