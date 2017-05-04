package com.student.action.android;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.StrutsStatics;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.student.bean.ClassFrom;
import com.student.bean.StudentForm;
import com.student.bean.TeacherForm;
import com.student.bean.UserForm;
import com.student.service.BaseService;
import com.student.util.JsonTools;
import com.student.util.MD5;

import freemarker.template.utility.StringUtil;

public class BaseActionAndroid extends ActionSupport {
	private BaseService baseService;
	private String serachkey;
	private String account;
	private String password;
	private String role;
	private String listStudent;
	private StudentForm studentForm;
	private TeacherForm teacherForm;
	private String listTeacher;
	private String listClass;
	private ClassFrom classFrom;
	private String studentGson;
	private String teacherGson;
	private String classGson;
	//上传文件存放路径   
    private final static String UPLOADDIR = "upload";   
    //上传文件集合   
    private File file;   
    //上传文件名集合   
    private List<String> fileFileName;   
    //上传文件内容类型集合   
    private List<String> fileContentType;
	
	private PrintWriter w = getPrintWriter();


	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}
	/**
	 * 
	 */
	public void findStudentByKey(){
		List<StudentForm> lists=baseService.findByProperty(StudentForm.class, "studentName", serachkey, 1);
		w.write(JsonTools.createJsonString(lists));
	}
	
	public void login(){
		password=MD5.getMd5(password);
		String hql="from UserForm where userAcct='"+account+"' and passwd='"+password+"'";
		List<UserForm> list=baseService.findByHql(hql);
		w.write(JsonTools.createJsonString(list));
	}
	
	public void register(){
		if (checkAccout(account)>0) {
			w.write("0");
		}else{
		UserForm form=new UserForm();
		form.setUserAcct(account);
		form.setPasswd(MD5.getMd5(password));
		form.setRoleSeq(Integer.parseInt(role));
		baseService.save(form);
		if (form.getRoleSeq()==1) {
			TeacherForm teacherForm=new TeacherForm();
			teacherForm.setTeacherAccount(form.getUserAcct());
			baseService.save(teacherForm);
		}else{
			StudentForm studentForm=new StudentForm();
			studentForm.setStudentAccout(form.getUserAcct());
			baseService.save(studentForm);
		}
		w.write("1");
		}
		
	}
	
	public void myData(){
		if ("1".equals(role)) {
			List<TeacherForm> list=(List<TeacherForm>)baseService.findByProperty(TeacherForm.class, "teacherAccount", account, 1);
			w.write(JsonTools.createJsonString(list!=null?list:null));
		}else{
			List<StudentForm> list=(List<StudentForm>)baseService.findByProperty(StudentForm.class, "studentAccout", account, 1);
			if (list!=null&&list.size()>0) {
				studentForm=(StudentForm) list.get(0);
				if (studentForm.getStudentCla()!=0) {
					classFrom=(ClassFrom) baseService.findById(ClassFrom.class, studentForm.getStudentCla());
				}
			}
			
			Map<String, String> map=new HashMap<String, String>();
			map.put("student", JsonTools.createJsonString(list));
			map.put("class", JsonTools.createJsonString(classFrom));
			w.write(JsonTools.createJsonString(map));
		}
		
	}
	public void findAllStu(){
		if ("".equals(serachkey)) {
			List<StudentForm> list=baseService.findAll(StudentForm.class);
			w.write(JsonTools.createJsonString(list));
		}else{
			String hql="from StudentForm where 1=1 and studentName like '%"+serachkey+"%' or studentAccout like '%"+serachkey+"%' or studentTel like '%"+serachkey+"%'";
			List<StudentForm> list=baseService.findByHql(hql);
			w.write(JsonTools.createJsonString(list));
		}
		
		
	}
	public void updateStudent(){
		studentForm=new StudentForm();
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		studentForm=gson.fromJson(studentGson, StudentForm.class);
		if (file!=null) {
			try {
				studentForm.setStudentUrlimage(uploadFile(studentForm.getStudentAccout()));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		baseService.update(studentForm);
		w.write("1");
	}
	
	public void addClass(){
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		ClassFrom classFrom=gson.fromJson(classGson, ClassFrom.class);
		baseService.save(classFrom);
		w.write("1");
	}
	
	public void updateClass(){
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		ClassFrom classFrom=gson.fromJson(classGson, ClassFrom.class);
		baseService.update(classFrom);
		w.write("1");
	}
	
	public void findAllTer(){
		List<TeacherForm> list=baseService.findAll(TeacherForm.class);
		w.write(JsonTools.createJsonString(list));
	}
	
	public void findAllClas(){
		if ("".equals(serachkey)) {
			String hql="from ClassFrom order by classNo desc";
			List<ClassFrom> list=baseService.findByHql(hql);
			w.write(JsonTools.createJsonString(list));
		}else{
			String hql="from ClassFrom where 1=1 and className like '%"+serachkey+"%' or classTeacher like '%"+serachkey+"%'";
			List<ClassFrom> list=baseService.findByHql(hql);
			w.write(JsonTools.createJsonString(list));
		}
	}
	
	public void updateTeacher(){
		teacherForm=new TeacherForm();
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		teacherForm=gson.fromJson(teacherGson,TeacherForm.class);
		if (file!=null) {
			try {
				teacherForm.setTeacherUrlimage(uploadFile(teacherForm.getTeacherAccount()));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		baseService.update(teacherForm);
		w.write("1");
	}
	
	public int checkAccout(String account){
		String hql="from UserForm where userAcct='"+account+"'";
		List list=baseService.findByHql(hql);
		System.out.println(list.size());
		return list.size();
		
	}
	
	public void saveStudent(){
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		StudentForm studentForm=gson.fromJson(listStudent, new TypeToken<StudentForm>(){}.getType());
		baseService.save(studentForm);
		w.write("1");
	}
	
	public void findAllClass(){
		List<ClassFrom> list=baseService.findAll(ClassFrom.class);
		w.write(JsonTools.createJsonString(list));
	}
	

	
	private String uploadFile(String name) throws FileNotFoundException, IOException {   
		File uploadFile=new File("");
        try {   
            InputStream in = new FileInputStream(file);
            String dir = ServletActionContext.getRequest().getRealPath(UPLOADDIR);  
            File fileLocation = new File(dir);  
            //此处也可以在应用根目录手动建立目标上传目录  
            if(!fileLocation.exists()){  
                boolean isCreated  = fileLocation.mkdir();  
                if(!isCreated) {  
                    //目标上传目录创建失败,可做其他处理,例如抛出自定义异常等,一般应该不会出现这种情况。  
                    return "";  
                }  
            }  
            uploadFile = new File(dir, name+".jpg");   
            OutputStream out = new FileOutputStream(uploadFile);   
            byte[] buffer = new byte[1024 * 1024];   
            int length;   
            while ((length = in.read(buffer)) > 0) {   
                out.write(buffer, 0, length);   
            }   
            in.close();   
            out.close(); 
        } catch (FileNotFoundException ex) {   
            System.out.println("上传失败!");  
            ex.printStackTrace();   
        } catch (IOException ex) {   
            System.out.println("上传失败!");  
            ex.printStackTrace();   
        }   
        return UPLOADDIR+"/"+name+".jpg";
    }  
	
	
	
	
	public PrintWriter getPrintWriter() {
		ActionContext ac = ActionContext.getContext();
		HttpServletResponse res = (HttpServletResponse) ac
				.get(StrutsStatics.HTTP_RESPONSE);
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter w = null;
		try {
			w = res.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return w;
	}
	
	

	
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public List<String> getFileFileName() {
		return fileFileName;
	}
	public void setFileFileName(List<String> fileFileName) {
		this.fileFileName = fileFileName;
	}
	public List<String> getFileContentType() {
		return fileContentType;
	}
	public void setFileContentType(List<String> fileContentType) {
		this.fileContentType = fileContentType;
	}
	public String getListStudent() {
		return listStudent;
	}

	public void setListStudent(String listStudent) {
		this.listStudent = listStudent;
	}

	public String getSerachkey() {
		return serachkey;
	}

	public void setSerachkey(String serachkey) {
		this.serachkey = serachkey;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getListTeacher() {
		return listTeacher;
	}

	public void setListTeacher(String listTeacher) {
		this.listTeacher = listTeacher;
	}

	public String getListClass() {
		return listClass;
	}

	public void setListClass(String listClass) {
		this.listClass = listClass;
	}
	public String getStudentGson() {
		return studentGson;
	}
	public void setStudentGson(String studentGson) {
		this.studentGson = studentGson;
	}
	public TeacherForm getTeacherForm() {
		return teacherForm;
	}
	public void setTeacherForm(TeacherForm teacherForm) {
		this.teacherForm = teacherForm;
	}
	public String getTeacherGson() {
		return teacherGson;
	}
	public void setTeacherGson(String teacherGson) {
		this.teacherGson = teacherGson;
	}
	public String getClassGson() {
		return classGson;
	}
	public void setClassGson(String classGson) {
		this.classGson = classGson;
	}

	
	
	

}
