export interface AuthService {
    login(username: string, password: string): Promise<LoginResponse>
    signUp(username: string, password: string): Promise<SignUpResponse>
}

export interface LoginResponse {
    sessionToken: string
}

export interface SignUpResponse {
    sessionToken: string
}
