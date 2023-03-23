import React, { FC, ReactNode, useContext } from 'react'
import { PresenterFactory } from '@/ui/PresenterFactory'

// @ts-ignore
export const PresentersContext = React.createContext<PresenterFactory>(undefined)

export const PresentersProvider: FC<Props> = (props) => (
    <PresentersContext.Provider value={props.factory}>
        {props.children}
    </PresentersContext.Provider>
)

export const usePresenterFactory = () => useContext(PresentersContext)

interface Props {
    factory: PresenterFactory,
    children: ReactNode,
}
