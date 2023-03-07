import { DependencyList } from 'react'
import { WebAppServices } from '@/ui/WebApp'
import { useAppServices } from '@/ui/components/context/AppServicesContext'
import { ChangeFunc } from '@/ui/lib/presenters/ChangeFunc'
import { usePresenter } from '@/ui/lib/presenters/usePresenter'

export function useAppPresenter<TPresenter>(
    presenterFactory: (onChange: ChangeFunc, services: WebAppServices) => TPresenter,
    startArgs: DependencyList = [],
): TPresenter {
    const services = useAppServices()
    return usePresenter(
        (onChange) => presenterFactory(onChange, services),
        startArgs
    )
}
