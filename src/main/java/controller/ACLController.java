package controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import exception.InvalidResourceException;
import exception.InvalidUserException;
import exception.ParameterMissingException;
import response.HasAccessResponse;
import service.AccessControlManager;

@RestController
public class ACLController {

    /**
     * Handler for accepting the incoming request (../access)
     * @param userId		user_id of the user
     * @param resourceId	resource_id for the resource to access
     * @param actionTypeValue	action_type type of access (DefaultValue => READ access)
     * @return	HasAccessResponse containing message to display and boolean result
     */
    @RequestMapping("/access")
    public HasAccessResponse hasAccess(@RequestParam(value="user_id", defaultValue="") String userId,
    		@RequestParam(value="resource_id", defaultValue="") String resourceId,
    		@RequestParam(value="action_type", defaultValue="0") String actionTypeValue) {
        
        try {
        	validateRequest(userId, resourceId, actionTypeValue);
		} catch (ParameterMissingException e) {
			return new HasAccessResponse("Request parameter missing: " + e.getMessage());
		}
        
        AccessControlManager.AccessControlBuilder builder = new AccessControlManager.AccessControlBuilder();
        AccessControlManager aclManager;
        boolean result = false;
        // To-DO: Can be replaced by global CONTENT configuration (user sensitive msg)
        String message = "Permission Denied";	// Default message
		try {
			aclManager = builder.withUserId(userId).
					withResourceId(resourceId).
					withActionType(actionTypeValue).
					build();
			result = aclManager.hasAccess();
			if(result){
				message = "Permission granted";
			}
		} catch (InvalidUserException e) {
			message = e.getMessage();
		} catch (InvalidResourceException e) {
			message = e.getMessage();
		}
        
    	return new HasAccessResponse(result, message);
    }
    
    /**
     * Validate the incoming request. Mostly deal with the compulsory parameters
     * @param userId		id of the user
     * @param resourceId	id of the resource to access
     * @param accessTypeValue	action type 
     * @throws ParameterMissingException	if any parameter missing
     */
    private void validateRequest(String userId, String resourceId, String accessTypeValue)
    		throws ParameterMissingException{
    	if(userId.isEmpty()){
    		throw new ParameterMissingException("user_id");
    	}
    	if(resourceId.isEmpty()){
    		throw new ParameterMissingException("resource_id");
    	}
    }
}
