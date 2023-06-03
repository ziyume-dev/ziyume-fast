import type { IAsyncRouteState } from '~/stores/modules/asyncRoute'
import type { ITabsViewState } from '~/stores/modules/tabsView'

export interface IStore {
  asyncRoute: IAsyncRouteState
  tabsView: ITabsViewState
  count: number
}
