package com.klaus.restapi;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.klaus.apiservice.SsdutEmpInfoService;
import com.klaus.bean.SsdutEmpInfo;
import com.klaus.factory.MyBeansFactory;

@Path("/ssdut")
public class SsdutEmpInfoAPI {

	@GET
	@Path("/empinfolist")
	@Produces(MediaType.APPLICATION_JSON)
	public List<SsdutEmpInfo> getEmpInfoList(@QueryParam("page") int pageIndex) {

		SsdutEmpInfoService service = (SsdutEmpInfoService) MyBeansFactory.getBeans("ssdutempinfoserviceimpl");

		return service.getEmpInfoList(pageIndex);

	}

	@GET
	@Path("/empinfodetail")
	@Produces(MediaType.TEXT_HTML)
	public String getEmpInfoDetail(@QueryParam("id") int id) {

		SsdutEmpInfoService service = (SsdutEmpInfoService) MyBeansFactory.getBeans("ssdutempinfoserviceimpl");

		return service.getEmpInfoDetail(id);

	}

}
