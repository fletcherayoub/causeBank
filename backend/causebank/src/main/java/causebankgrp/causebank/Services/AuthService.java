package causebankgrp.causebank.Services;

import causebankgrp.causebank.Dto.Response.AuthResponse;
import causebankgrp.causebank.Dto.request.LoginRequest;
import causebankgrp.causebank.Dto.request.SignupRequest;
// import causebankgrp.causebank.Models.UserModel;
// 5
public interface AuthService {
    AuthResponse signup(SignupRequest request);
    AuthResponse login(LoginRequest request);
    void logout(String token);
}
