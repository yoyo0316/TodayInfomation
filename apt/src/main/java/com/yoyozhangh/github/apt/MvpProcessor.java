package com.yoyozhangh.github.apt;

import com.google.auto.service.AutoService;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.TypeElement;

@AutoService(Process.class)
@SupportedAnnotationTypes({"com.yoyozhangh.github.annotation.MvpEmptyViewFactory"})
public class MvpProcessor extends AbstractProcessor {

    public Filer mFiler;

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        mFiler = processingEnv.getFiler();
        new MvpEmptyViewProcessor().process(roundEnvironment, this);
        return true;
    }
}
