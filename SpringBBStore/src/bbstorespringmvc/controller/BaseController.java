package bbstorespringmvc.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import bbstorespringmvc.model.BaseEntity;

@Transactional
public class BaseController {
    
    @PersistenceContext
    EntityManager em;
    
    protected Object getRequiredEntity(long id, Class<? extends BaseEntity> clazz) {
        Object obj =  em.find(clazz, id );
        if (obj == null) {
            throw new RuntimeException("Indvalid URL. The " + clazz.getName() + " with id '"+id+"' is not found.");
        }
        return obj;
    }    
    
    protected Object getRequiredDetachedEntity(long id, Class<? extends BaseEntity> clazz) {
    	Object entity = getRequiredEntity(id, clazz);
    	em.detach(entity);
    	return entity;
    }

    

//     * Our requestMapping controller method may refuse to validate the changes and if the entity is still attached,
//     * the Hibernate's dirty checking might save the entity (which we do not want). 
//     * @param id
//     * @return
//     */
//
//    /**
//     * handle AjaxValidationException and return an AjaxExceptionVO to get message in jQuery.error() 
//     * @see https://doanduyhai.wordpress.com/2012/05/06/spring-mvc-part-v-exception-handling/
//     */
//    @ExceptionHandler(AjaxValidationException.class)
//    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
//    @ResponseBody
//    protected AjaxExceptionVO handleAjaxValidationException(AjaxValidationException ex, HttpServletResponse response) throws IOException
//    {
//    	AjaxExceptionVO exceptionVO = new AjaxExceptionVO("handleAjaxValidationException()", this.getClass().toString(), ex.getMessage());
//    	return exceptionVO;
//    }
//    
//    /**
//     * This method will return a premade model and view to go on confiorm page before the deletion of some Entities.
//     * 
//     * @param message 
//     * @param deleteMethodAdress address of the controller method 
//     * @param urlInCaseOfAbortion
//     * @param id
//     * @return 
//     */
//    protected ModelAndView getConfirmBeforeDeletePage(String message,String deleteMethodAdress,String urlInCaseOfAbortion,Long id){
//
//        ModelAndView mv = new ModelAndView("confirm");
//        mv.addObject("url",deleteMethodAdress);
//        mv.addObject("info",message);
//        mv.addObject("id",id);
//        mv.addObject("abortUrl",urlInCaseOfAbortion);
//        return mv;
//    }
}
