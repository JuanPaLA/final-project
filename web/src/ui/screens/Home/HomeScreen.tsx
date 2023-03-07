import { WebAppServices } from '@/ui/WebApp'
import { HomePresenter } from '@/ui/screens/Home/HomePresenter'
import { useAppPresenter } from '@/ui/lib/presenters/useAppPresenter'
import styled from 'styled-components'

const homePresenter = (onChange, services: WebAppServices) =>
    new HomePresenter(onChange, services.core, services.router)

export const HomeScreen = () => {
    const presenter = useAppPresenter(homePresenter)
    return (
        <div>
            <h1>Hola mundo</h1>
            <Counter>Counter: {presenter.model.counter}</Counter>
            <a href="#" onClick={() => presenter.increment()}>Incrementar</a>
            <label>Nombre:</label>
            <input value={presenter.model.username} onChange={(e) => presenter.setUsername(e.target.value)} />
            <label>Password:</label>
            <input value={presenter.model.password} onChange={(e) => presenter.setPassword(e.target.value)} />
            <a href="#" onClick={() => presenter.signup()}>Signup</a>
        </div>
    )
}

const Counter = styled.div`
  color: red;
`
