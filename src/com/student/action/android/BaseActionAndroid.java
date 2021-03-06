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
import com.student.bean.CourseForm;
import com.student.bean.ScoreForm;
import com.student.bean.StudentForm;
import com.student.bean.TeacherForm;
import com.student.bean.TestFrom;
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
	private String courseGson;
	private String testGson;
	private String scoreGson;
	//上传文件存放路径   
    private final static String UPLOADDIR = "upload";   
    //上传文件集合   
    private File file;   
    //上传文件名集合   
    private List<String> fileFileName;   
    //上传文件内容类型集合   
    private List<String> fileContentType;
	
	private PrintWriter w = getPrintWriter();

	Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
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
			Map<String, String> map=new HashMap<String, String>();
			List<StudentForm> list=(List<StudentForm>)baseService.findByProperty(StudentForm.class, "studentAccout", account, 1);
			if (list!=null&&list.size()>0) {
				studentForm=(StudentForm) list.get(0);
				map.put("student", JsonTools.createJsonString(studentForm));
				if (studentForm.getStudentCla()!=0) {
					classFrom=(ClassFrom) baseService.findById(ClassFrom.class, studentForm.getStudentCla());
					map.put("class", JsonTools.createJsonString(classFrom));
				}
			}
			List user=baseService.findByProperty(UserForm.class, "userAcct", account, 1);
			if (user!=null&&user.size()>0) {
				map.put("user", JsonTools.createJsonString(user.get(0)));
			}
			w.write(JsonTools.createJsonString(map));
		}
		
	}
	public void delStuAndAcou(){
		List<StudentForm> list=(List<StudentForm>)baseService.findByProperty(StudentForm.class, "studentAccout", account, 1);
		if (list!=null&&list.size()>0) {
			studentForm=(StudentForm) list.get(0);
			baseService.delete(studentForm);
		}
		List<UserForm> user=baseService.findByProperty(UserForm.class, "userAcct", account, 1);
		if (user!=null&&user.size()>0) {
			UserForm u=user.get(0);
			baseService.delete(u);
		}
		w.write("1");
	}
	public void findAllStu(){
		if ("".equals(serachkey) || serachkey==null) {
			List<StudentForm> list=baseService.findAll(StudentForm.class);
			w.write(JsonTools.createJsonString(list));
		}else{
			String hql="from StudentForm where 1=1 and studentName like '%"+serachkey+"%' or studentAccout like '%"+serachkey+"%' or studentTel like '%"+serachkey+"%'";
			List<StudentForm> list=baseService.findByHql(hql);
			w.write(JsonTools.createJsonString(list));
		}
		
		
	}
	public void findStuByClassId(){
		if (serachkey==null || serachkey=="" || "0".equals(serachkey)) {
			List<StudentForm> list=baseService.findAll(StudentForm.class);
			w.write(JsonTools.createJsonString(list));
		}else{
		List<StudentForm> list=baseService.findByProperty(StudentForm.class, "studentCla", Integer.valueOf(serachkey), 1);
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
	
	public void addStudent(){
		Map<String, String> map=gson.fromJson(studentGson,new TypeToken<Map<String, String>>(){}.getType());
		UserForm userForm=gson.fromJson(map.get("user"), UserForm.class);
		if (userForm.getUserId()==null) {
			userForm.setPasswd(MD5.getMd5(userForm.getPasswd()));
			if (checkAccout(userForm.getUserAcct())>0) {
				w.write("0");
			}else{
				StudentForm studentForm=gson.fromJson(map.get("student"), StudentForm.class);
				baseService.save(userForm);
				baseService.save(studentForm);
				w.write("1");
			}
		}else{
			StudentForm studentForm=gson.fromJson(map.get("student"), StudentForm.class);
			baseService.update(userForm);
			baseService.update(studentForm);
			w.write("1");
		}
		
		
		
	}
	
	public void delCLass(){
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		ClassFrom classFrom=gson.fromJson(classGson, ClassFrom.class);
		baseService.delete(classFrom);
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
		if ("".equals(serachkey) || serachkey==null) {
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
	
	public void findCourse(){
		List<CourseForm> list=new ArrayList<CourseForm>();
		if (serachkey!=null && !"".equals(serachkey)) {
			list=baseService.findByProperty(CourseForm.class, "courseName", serachkey, 2);
		}else{
			list=baseService.findAll(CourseForm.class);
		}
		w.write(JsonTools.createJsonString(list));
	}
	
	public void addOrUpCourse(){
		CourseForm courseForm=gson.fromJson(courseGson, CourseForm.class);
		if (courseForm.getCourseId()!=null) {
			baseService.update(courseForm);
		}else{
			baseService.save(courseForm);	
		}
		w.write("1");
	}
	
	public void delCourse(){
		CourseForm courseForm=gson.fromJson(courseGson, CourseForm.class);
		baseService.delete(courseForm);
		w.write("1");
	}
	
	public void findTest(){
		List<TestFrom> list=new ArrayList<TestFrom>();
		if (serachkey!=null && !"".equals(serachkey)) {
			String hql="from TestFrom where 1=1 and testQihao like '%"+serachkey+"%' or testName like '%"+serachkey+"%'";
			list=baseService.findByHql(hql);
		}else{
			list=baseService.findAll(TestFrom.class);
		}
		w.write(JsonTools.createJsonString(list));
	}
	
	public void findTestByCourseId(){
		List<TestFrom> list=new ArrayList<TestFrom>();
		if ("0".equals(serachkey) || null==serachkey || serachkey=="") {
			list=baseService.findAll(TestFrom.class);
		}else{
			list=baseService.findByProperty(TestFrom.class, "courseId", Integer.valueOf(serachkey), 1);
		}
		w.write(JsonTools.createJsonString(list));
	}
	
	public void addOrUpTest(){
		TestFrom t=gson.fromJson(testGson, TestFrom.class);
		if (t.getId()!=null) {
			baseService.update(t);
		}else{
			baseService.save(t);
		}
		w.write("1");
	}
	
	public void delTest(){
		TestFrom courseForm=gson.fromJson(testGson, TestFrom.class);
		baseService.delete(courseForm);
		w.write("1");
	}
	
	public void findScore(){
		Map<String, String> map=new HashMap<String, String>();
		String courseId="";
		String testId="";
		String classNo="";
		String studentId="";
		StringBuffer hql=new StringBuffer();
		if (serachkey!=null && serachkey!=""&&!"".equals(serachkey)) {
			map=gson.fromJson(serachkey,new TypeToken<Map<String, String>>(){}.getType());
			if (map.size()!=0) {
				courseId=map.get("courseId");
				 testId=map.get("testId");
				 classNo=map.get("classNo");
				 studentId=map.get("studentId");
				 hql.append("from ScoreForm sco,CourseForm cou,TestFrom tes,ClassFrom cla,StudentForm stu " +
							"where cou.courseId=tes.courseId and sco.testId=tes.id and sco.scoreCls=cla.classNo " +
							"and cla.classNo=stu.studentCla");
				 if (!"0".equals(courseId)) {
					hql.append(" and cou.courseId="+courseId);
				}
				 if (!"0".equals(testId)) {
					hql.append(" and sco.testId="+testId);
				}
				 if (!"0".equals(classNo)) {
					hql.append(" and cla.classNo="+classNo);
				}
				 if (!"0".equals(studentId)) {
					hql.append(" and stu.studentId="+studentId);
				}
			}else{
				hql.append("from ScoreForm sco,CourseForm cou,TestFrom tes,ClassFrom cla,StudentForm stu " +
						"where cou.courseId=tes.courseId and sco.testId=tes.id and sco.scoreCls=cla.classNo " +
						"and cla.classNo=stu.studentCla ");
			}
			 
			 
		}else{
			hql.append("from ScoreForm sco,CourseForm cou,TestFrom tes,ClassFrom cla,StudentForm stu " +
					"where cou.courseId=tes.courseId and sco.testId=tes.id and sco.scoreCls=cla.classNo " +
					"and cla.classNo=stu.studentCla ");
		}
		
		
		List list=baseService.findByHql(hql.toString());
		w.write(JsonTools.createJsonString(list));
	}
	
	public void saveScore(){
		ScoreForm s=gson.fromJson(scoreGson, ScoreForm.class);
		if (checkScore(s)) {
			w.write("0");
		}else{
			baseService.save(s);
			w.write("1");
		}
		
	}
	
	public boolean checkScore(ScoreForm s){
		String hql="from ScoreForm where studentNo="+s.getStudentNo()+" and testId="+s.getTestId();
		List l=baseService.findByHql(hql);
		if (l!=null&&l.size()!=0) {
			return true;
		}else{
			return false;
		}
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

	public String getScoreGson() {
		return scoreGson;
	}
	public void setScoreGson(String scoreGson) {
		this.scoreGson = scoreGson;
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
	
	
	public String getCourseGson() {
		return courseGson;
	}
	public void setCourseGson(String courseGson) {
		this.courseGson = courseGson;
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
	public String getTestGson() {
		return testGson;
	}
	public void setTestGson(String testGson) {
		this.testGson = testGson;
	}

	
	
	

}
