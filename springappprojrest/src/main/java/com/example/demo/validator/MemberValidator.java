package com.example.demo.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.example.demo.model.Member;

public class MemberValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Member.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Member member = (Member) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "member.name.empty", "姓名欄不能為空白");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "memberId", "member.memberId.empty", "帳號欄不能為空白");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "balance", "member.balance.empty", "餘額欄不能為空白");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "birthday", "member.birthday.empty", "生日欄不能為空白");
		if (!errors.hasFieldErrors("memberId")) {
			if (member.getMemberId() != null && member.getMemberId().length() <= 3) {
				errors.rejectValue("memberId", "member.memberId.length", "帳號欄長度必須大於等於四");
			}
		}
		if (!errors.hasFieldErrors("name")) {
			if (member.getName() != null && member.getName().length() < 2) {
				errors.rejectValue("name", "member.name.length", "姓名欄長度必須大於等於二");
			}
		}
	}

}
