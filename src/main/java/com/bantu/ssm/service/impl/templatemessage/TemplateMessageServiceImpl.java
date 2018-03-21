package com.bantu.ssm.service.impl.templatemessage;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bantu.ssm.dao.templatemessage.TemplateMessageDao;
import com.bantu.ssm.entity.templatemessage.TemplateMessage;
import com.bantu.ssm.service.templatemessage.TemplateMessageService;
import com.bantu.ssm.util.PageUtil;
@Service
@Transactional
public class TemplateMessageServiceImpl implements TemplateMessageService{
	@Autowired
	private TemplateMessageDao emplateMessageDao;

	@Override
	public List<TemplateMessage> getPageList(Map map) {
		// TODO Auto-generated method stub
		return emplateMessageDao.getPageList(map);
	}

	@Override
	public int getCount(Map map) {
		// TODO Auto-generated method stub
		return emplateMessageDao.getCount(map);
	}

	@Override
	public PageUtil getPage(Map map) {
		// TODO Auto-generated method stub
		List<TemplateMessage> pageList = emplateMessageDao.getPageList(map);
		Object object = map.get("rows");
		Object object2 = map.get("page");
		System.out.println(object2+"-*--*-**-**--*-*"+pageList.size()+object);
		return new PageUtil(pageList,emplateMessageDao.getCount(map));
	}

	@Override
	public int add(TemplateMessage templateMessage) {
		// TODO Auto-generated method stub
		return emplateMessageDao.add(templateMessage);
	}

	@Override
	public int update(TemplateMessage templateMessage) {
		// TODO Auto-generated method stub
		return emplateMessageDao.update(templateMessage);
	}

	@Override
	public List<TemplateMessage> getById(String TemplateMessage_id) {
		// TODO Auto-generated method stub
		return emplateMessageDao.getById(TemplateMessage_id);
	}
	

}
