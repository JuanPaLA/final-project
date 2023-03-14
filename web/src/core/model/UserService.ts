export interface UserService {
    signup(name: string, password: string): Promise<void>
}
