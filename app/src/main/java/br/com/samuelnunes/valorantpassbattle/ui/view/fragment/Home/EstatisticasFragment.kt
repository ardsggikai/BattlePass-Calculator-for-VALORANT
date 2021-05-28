package br.com.samuelnunes.valorantpassbattle.ui.view.fragment.Home

import br.com.samuelnunes.valorantpassbattle.ui.view.fragment.Infos.ProgressFragment
import br.com.samuelnunes.valorantpassbattle.ui.view.fragment.Infos.ProjectionsFragment
import br.com.samuelnunes.valorantpassbattle.ui.view.fragment.Infos.TimelineFragment


class EstatisticasFragment :
    PagerSliderFragment(listOf(ProgressFragment(), TimelineFragment(), ProjectionsFragment()))

