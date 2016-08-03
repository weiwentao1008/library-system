package com.library.web.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.library.web.bean.Book;
import com.library.web.bean.Borrow;
import com.library.web.exception.WebException;
import com.library.web.service.imp.BookService;
import com.library.web.service.imp.BorrowService;

@Controller
@RequestMapping(value = "/borrow")
public class BorrowController extends BaseController {

	@ResponseBody
	@RequestMapping(value="/borrowBook",method = RequestMethod.POST)
	public Map<String, Object> BorrowBook(@RequestParam("bookisbn") String isbn,@RequestParam("readerid") Integer userid)
	{

		Map<String,Object> modelMap=new HashMap<String,Object>();
		int state=BorrowService.borrowBook(isbn, userid);
		switch(state)
		{
		case 1:modelMap.put("state","超过可借阅数");throw new WebException("借书失败");
		case 2:modelMap.put("state","找不到isbn或用户");throw new WebException("借书失败");
		case 3:modelMap.put("state","借阅成功,请及时归还······");break;
		case 4:modelMap.put("state","借阅失败,请重试·····");throw new WebException("借书失败");
		}
		return modelMap;
	}
	
	@ResponseBody 
	@RequestMapping(value="/borrowBookinfo",method=RequestMethod.GET)
	public static Map<String,Object> getBorrowBookInfo(@RequestParam("readerid") Integer id,@RequestParam("logincode") String code)
	{
		Map<String,Object> modelMap=new HashMap<String,Object>();
		Integer loginid=Integer.valueOf(getUserIdByCode(code));
		if(id==null)
		{
			if(loginid==null)
				throw new WebException("查询借书信息失败");
			else
				id=loginid;
		}
		
		List<Borrow> list=BorrowService.findForuserid(id);
		List<Map>maplist=new LinkedList<Map>();
		int num=0;
		Calendar cal=Calendar.getInstance();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Date now=cal.getTime();
		for(int i=0;i<list.size();i++)
		{
			Map<String,Object> temp=new HashMap<String,Object>();
			
			Book book=BookService.getBookInfo(list.get(i).getBookid());
			temp.put("borrowid",list.get(i).get_id());
			temp.put("bookname", book.getName());
			temp.put("bookid",book.get_id());
			temp.put("backtime",list.get(i).getBack_time());
			temp.put("borrowtime",list.get(i).getBorrow_time());
			try {
				if(sf.parse(list.get(i).getBack_time()).before(now))
					num++;
			} catch (ParseException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			if(list.get(i).getIsback()==0)
				temp.put("isback","未归还");
			else
				temp.put("isback","已归还");
			maplist.add(temp);
		}
		if(list.size()<=0)
			throw new WebException("查询借书信息失败");
		else
		{
			modelMap.put("moretime",num);
			modelMap.put("borrowList", maplist);
		}
		
		return modelMap;
			
	}
	
	@ResponseBody 
	@RequestMapping(value="/hasborrowBookinfo",method=RequestMethod.GET)
	public static Map<String,Object> gethasBorrowBookInfo(@RequestParam("logincode") String code)
	{
		Map<String,Object> modelMap=new HashMap<String,Object>();
		Integer id=Integer.valueOf(getUserIdByCode(code));
		List<Borrow> list=BorrowService.findForuserid(id);
		List<Map<String,Object>>maplist=new LinkedList<Map<String,Object>>();
		int num=0;
		Calendar cal=Calendar.getInstance();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Date now=cal.getTime();
		for(int i=0;i<list.size();i++)
		{
			Map<String,Object> temp=new HashMap<String,Object>();
			
			Book book=BookService.getBookInfo(list.get(i).getBookid());
			temp.put("borrowid",list.get(i).get_id());
			temp.put("bookname", book.getName());
			temp.put("bookid",book.get_id());
			temp.put("backtime",list.get(i).getBack_time());
			temp.put("borrowtime",list.get(i).getBorrow_time());
			try {
				if(sf.parse(list.get(i).getBack_time()).before(now))
					num++;
			} catch (ParseException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			if(list.get(i).getIsback()==0)
				temp.put("isback","未归还");
			else
				temp.put("isback","已归还");
			maplist.add(temp);
		}
		if(list.size()<=0)
			throw new WebException("查询借书信息失败");
		else
		{
			modelMap.put("moretime",num);
			modelMap.put("borrowList", maplist);
		}
		return modelMap;
			
	}
	
	@ResponseBody
	@RequestMapping(value="/backBook",method=RequestMethod.PUT)
	public static Map<String,Object> backBook(@RequestParam("borrowid") Integer borrowid)
	{
		Map<String,Object> modelMap=new HashMap<String,Object>();
		if(BorrowService.BackBook(borrowid))
			return modelMap;
		else
			throw new WebException("还书失败");
		
	}
	
}
