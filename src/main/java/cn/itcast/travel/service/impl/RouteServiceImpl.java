package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.dao.RouteImgDao;
import cn.itcast.travel.dao.SellerDao;
import cn.itcast.travel.dao.impl.RouteDaoImpl;
import cn.itcast.travel.dao.impl.RouteImgDaoImpl;
import cn.itcast.travel.dao.impl.SellerDaoImpl;
import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.RouteImg;
import cn.itcast.travel.domain.Seller;
import cn.itcast.travel.service.RouteService;

import java.util.List;

public class RouteServiceImpl implements RouteService {
    private RouteDao routeDao=new RouteDaoImpl();
    private RouteImgDao routeImgDao=new RouteImgDaoImpl();
    private SellerDao sellerDao=new SellerDaoImpl();
    @Override
    public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize ,String rname) {
        //封装PageBean
        PageBean<Route> pb=new PageBean<Route>();
        //设置当前页码
        pb.setCurrentPage(currentPage);
        //设置每页显示条数
        pb.setPageSize(pageSize);

        //设置总记录数
        int totalCount=routeDao.findTotalCount(cid,rname);
        pb.setTotalCount(totalCount);
        //设置当前页显示的数据集合
        int start=(currentPage-1)*pageSize;
        List<Route> list=routeDao.findByPage(cid,start,pageSize,rname);
        pb.setList(list);

        //设置总页数=总记录数/每页显示的条数
        int totalPage=totalCount%pageSize==0? totalCount/pageSize:(totalCount/pageSize)+1;
        pb.setTotalPage(totalPage);
        return pb;
    }

    @Override
    public Route findOne(String rid) {
        Route route=routeDao.findOne(Integer.parseInt(rid));

        //根据route id查询图片集合信息
    List<RouteImg> routeImgList=routeImgDao.findByRid(route.getRid());
    //
        route.setRouteImgList(routeImgList);

        //查询卖家的信息
        Seller seller=sellerDao.findById(route.getSid());
        route.setSeller(seller);

        return route;
    }
}
