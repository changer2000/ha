package com.etech.ha.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.etech.ha.mst.bean.HolidayListSearchBean;
import com.etech.ha.peer.HolidayListPeer;
import com.etech.system.dao.BaseDao;

@Repository
public class HolidayListDAO extends BaseDao<HolidayListPeer> {
	
	public List<HolidayListPeer> search(HolidayListSearchBean searchBean) {
		boolean bAddedWhere = false;
		List<Object> paramList = new ArrayList<Object>();
		
		StringBuilder hqlBuf = new StringBuilder("from HolidayListPeer a");
		//hqlBuf.append(" left join HolidayListDtlPeer b where a.id=b.hldyListPeer.id ");	//这是一个失败的查询例子。供以后参考
		hqlBuf.append(" left join fetch a.hldyListDtlList b  ");
		
		if (searchBean.getHldy_id()!=null) {
			if (!bAddedWhere) {
				bAddedWhere = true;
				hqlBuf.append(" where ");
			} else {
				hqlBuf.append(" and ");
			}
			hqlBuf.append(" a.holidayPeer.id=? ");
			paramList.add(searchBean.getHldy_id());
		}
		
		if (searchBean.getHldy_year()!=null) {
			if (!bAddedWhere) {
				bAddedWhere = true;
				hqlBuf.append(" where ");
			} else {
				hqlBuf.append(" and ");
			}
			hqlBuf.append(" a.hldy_year=? ");
			paramList.add(searchBean.getHldy_year());
		}
		
		if (searchBean.getStart_date()!=null) {
			if (!bAddedWhere) {
				bAddedWhere = true;
				hqlBuf.append(" where ");
			} else {
				hqlBuf.append(" and ");
			}
			hqlBuf.append(" a.start_dt>=? ");
			paramList.add(searchBean.getStart_date());
		}
		if (searchBean.getEnd_date()!=null) {
			if (!bAddedWhere) {
				bAddedWhere = true;
				hqlBuf.append(" where ");
			} else {
				hqlBuf.append(" and ");
			}
			hqlBuf.append(" a.end_dt<=? ");
			paramList.add(searchBean.getEnd_date());
		}
		if (searchBean.getHldy_start()!=null) {
			if (!bAddedWhere) {
				bAddedWhere = true;
				hqlBuf.append(" where ");
			} else {
				hqlBuf.append(" and ");
			}
			hqlBuf.append(" b.hldy_start>=? ");
			paramList.add(searchBean.getEnd_date());
		}
		if (searchBean.getHldy_end()!=null) {
			if (!bAddedWhere) {
				bAddedWhere = true;
				hqlBuf.append(" where ");
			} else {
				hqlBuf.append(" and ");
			}
			hqlBuf.append(" b.hldy_end<=? ");
			paramList.add(searchBean.getEnd_date());
		}
		hqlBuf.append(" order by a.start_dt,b.hldy_start");
		
		
		Object[] params = paramList.toArray();
		return (List<HolidayListPeer>) createQuery(hqlBuf.toString(), params).list();
	}
	
}
