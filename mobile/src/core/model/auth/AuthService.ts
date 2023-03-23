export interface AuthService {
    login(username: string, password: string): Promise<LoginResponse>
    signUp(name: string, password: string): Promise<void>
}

export interface LoginResponse {
    sessionToken: string
}

export interface SignUpResponse {
    sessionToken: string
}
