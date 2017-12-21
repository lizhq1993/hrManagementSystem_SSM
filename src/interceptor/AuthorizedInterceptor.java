package interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import common.HrmConstants;
import domain.User;

/*
 * �ж��û�Ȩ�޵�spring mvc��������
 */
public class AuthorizedInterceptor implements HandlerInterceptor{
	
	private static final String[] IGNORE_URI={"/loginForm","/login","/404.html"}; //���岻��Ҫ���ص�����
	
	/*
	 * �÷�����ҪpreHandler�����ķ���ֵΪtrueʱ�Ż�ִ��
	 * �÷������������������֮��ִ�У���Ҫ����������������Դ
	 * @see org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	/*
	 * �÷�����ҪpreHandler�����ķ���ֵΪtrueʱ�Ż�ִ��
	 * ִ��ʱ�����ڴ��������д���֮��Ҳ������controller�ķ�������֮��ִ��
	 * @see org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mv)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	/*
	 * preHandler������ ���д����������õģ��÷�������controller����֮ǰ���е���
	 * ��preHandler�ķ���ֵΪfalse��ʱ����������ͽ�����
	 * �������ֵΪtrue��������ִ��postHandler��afterCompletion
	 * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		boolean flag=false; //Ĭ���û�û�е�½
		String servletPath=request.getServletPath();
		for(String s:IGNORE_URI){
			if(servletPath.contains(s)){
				flag=true;
				break;
			}
		}
		if(!flag){
			User user=(User)request.getSession().getAttribute(HrmConstants.USER_SESSION);
			if(user==null){
				request.setAttribute("message", "���ȵ�¼�ٷ�����վ��");
				request.getRequestDispatcher(HrmConstants.LOGIN).forward(request, response);
				return flag;
			}else{
				flag=true;
			}
		}
		return flag;
	}

}
