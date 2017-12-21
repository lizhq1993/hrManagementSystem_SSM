package interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import common.HrmConstants;
import domain.User;

/*
 * 判断用户权限的spring mvc的拦截器
 */
public class AuthorizedInterceptor implements HandlerInterceptor{
	
	private static final String[] IGNORE_URI={"/loginForm","/login","/404.html"}; //定义不需要拦截的请求
	
	/*
	 * 该方法需要preHandler方法的返回值为true时才会执行
	 * 该方法将在整个请求完成之后执行，主要作用是用于清理资源
	 * @see org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	/*
	 * 该方法需要preHandler方法的返回值为true时才会执行
	 * 执行时间是在处理器进行处理之后，也就是在controller的方法调用之后执行
	 * @see org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mv)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	/*
	 * preHandler方法是 进行处理器拦截用的，该方法将在controller处理之前进行调用
	 * 当preHandler的返回值为false的时候整个请求就结束了
	 * 如果返回值为true，则会继续执行postHandler和afterCompletion
	 * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		boolean flag=false; //默认用户没有登陆
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
				request.setAttribute("message", "请先登录再访问网站！");
				request.getRequestDispatcher(HrmConstants.LOGIN).forward(request, response);
				return flag;
			}else{
				flag=true;
			}
		}
		return flag;
	}

}
