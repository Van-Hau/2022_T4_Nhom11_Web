package Controller;

import java.io.IOException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import Model.DataTemp;
import Model.DateDim;
import Model.Area;
import Model.Award;
import Model.Data;
import Model.Province;
import Service.Api;

/**
 * Servlet implementation class Home
 */
@WebServlet("/Home")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Home() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain;charset=UTF-8;pageEncoding=UTF-8");
		// TODO Auto-generated method stub
		List<Data> data=Data.getByDate("2022-01-24", "MN");
		String dataString="";
		Map<String,List<Data>> map=new HashMap<String, List<Data>>();
		for(Data d:data) {
			String province=d.getProvince_Fact();
			if(map.containsKey(province)) {
				map.get(province).add(d);
			}
			else {
				List<Data> elements=new ArrayList<Data>();
				elements.add(d);
				map.put(province, elements);
			}
		}
		for(Entry<String, List<Data>> entry:map.entrySet()) {
			dataString+=entry.getKey()+"\n";
			for(Data e:entry.getValue()) {
				dataString+=e.getNumberResultReal()+"\n";
			}
			dataString+="--------------\n";
		}
		
//		for(Data d:data) {
//			dataString+=d.getNumberResultReal()+"\n";
//		}
//		data.setArea(1);
//		boolean update=Data.update(Data.getData(data));
//		data.setId("test1");
//		boolean insert=Data.insert(Data.getData(data));
//		boolean delete=Data.delete(data.getId());
		response.getWriter().println("OK CHUA - \n "+"\n"+dataString
				);
		//request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
