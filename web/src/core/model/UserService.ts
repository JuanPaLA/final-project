export interface UserService {
    signup(name: string, password: string): Promise<void>
    login(name: void, password: string): Promise<void>
}
