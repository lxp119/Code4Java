package com.luxx.dao;

import com.luxx.model.BaseStation;
import com.luxx.service.IBaseStationOperation;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class BaseStationDb {
    private static SqlSessionFactory sqlSessionFactory;

    static {
        try {
            // 通过配置文件初始化sqlSessionFactory
            Reader reader = Resources.getResourceAsReader("config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<BaseStation> getAllBaseStations() {
        List<BaseStation> stations = new ArrayList<BaseStation>();
        SqlSession session = sqlSessionFactory.openSession();
        try {
            IBaseStationOperation stationOperation = session.getMapper(IBaseStationOperation.class);
            stations = stationOperation.getAllBaseStation();
        } finally {
            session.close();
        }

        return stations;
    }
}

