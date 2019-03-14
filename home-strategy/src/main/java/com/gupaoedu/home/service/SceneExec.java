package com.gupaoedu.home.service;

import com.gupaoedu.home.service.jsoncheck.Root;
import com.gupaoedu.home.service.strategy.AbstractScenes;
import com.gupaoedu.home.service.strategy.ScenesStrategy;

import java.util.List;

public class SceneExec {

    public int exec(Root root) {

        List<AbstractScenes> scenes = ScenesStrategy.getScenes(root);

        for (AbstractScenes abstractScenes : scenes) {
            //一个个执行
            int i = abstractScenes.decisionScenes();
            if (i != -1) {
                //有可能满足场景1的时候，还会满足场景2
                return i;
            }
        }
        return -1;
    }

}
