package com.example.nagoyameshi.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.nagoyameshi.entity.Users;
import com.example.nagoyameshi.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	private UserRepository userRepository;
	
	public UserDetailsServiceImpl(UserRepository userRepository)
	{
		this.userRepository=userRepository;
	}

	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
	{
		try {
			Users user=userRepository.findByEmail(email);
			
			String userRoleName=user.getRole().getName();
			
			Collection<GrantedAuthority>authorities=new ArrayList<>();
			
			authorities.add(new SimpleGrantedAuthority(userRoleName));
			System.out.println("ユーザ－情報:"+user);
			return new UserDetailsImpl(user,authorities );
		}
		catch(Exception e)
		{
			System.out.println("ユーザ－情報:");
			 throw new UsernameNotFoundException("ユーザーが見つかりませんでした。");
			 
		}
	}
	
}
