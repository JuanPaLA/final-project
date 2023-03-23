import React from 'react'
import { Text, View } from 'react-native'
import { usePresenterFactory } from '@/ui/components/context/PresentersContext'
import { usePresenter } from '@/ui/services/presenters/usePresenter'

export const HomeScreen = ({ navigator: navigation }) => {
    const presenters = usePresenterFactory()
    const presenter = usePresenter(presenters.home)

    return (
        <View>
            <Text>Home screen!</Text>
        </View>
    )
}
