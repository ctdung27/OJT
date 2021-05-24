package ait.team.java.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class MessageUtil {
	public Map<String, String> getMessage(String message) {
		Map<String, String> result = new HashMap<>();
		
		if (message.equals("changes_success")) {
			result.put("message", "Success! You Changed Status Event.");
			result.put("alert", "success");
		} else if (message.equals("delete_success")) {
			result.put("message", "Success! You Deleted Event.");
			result.put("alert", "success");
		} else if (message.equals("error_system")) {
			result.put("message", "Error System !!!");
			result.put("alert", "danger");
		}
		return result;
	}
}
