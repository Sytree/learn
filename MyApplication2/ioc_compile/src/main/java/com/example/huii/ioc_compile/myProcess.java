package com.example.huii.ioc_compile;

import com.example.huii.annotation_hui.SomethingOne;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;

@AutoService(Processor.class)
public class myProcess extends AbstractProcessor {
    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Collections.singleton(SomethingOne.class.getCanonicalName());

    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        String sss = "";
        for (Element element : roundEnv.getElementsAnnotatedWith(SomethingOne.class)) {
//            AnnotatedClass annotatedClass = getAnnotatedClass(element);
//            annotatedClass.addField(bindViewField);
            sss += "EnclosingElement : " + element.getEnclosingElement().getSimpleName() + " --> ";

            List<? extends Element> enclosedElements = element.getEnclosedElements();
            sss += "enclosedElements : ";

            for (Element enclosedElement : enclosedElements) {
                sss += "enclosedElement" + enclosedElement.getSimpleName();
            }

            sss += "elemet" + element.getSimpleName() + " end";
        }


        MethodSpec main = MethodSpec.methodBuilder("main")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(void.class)
                .addParameter(String[].class, "args")
                .addStatement("$T.out.println($S)", System.class, sss)
                .build();

        TypeSpec helloWorld = TypeSpec.classBuilder("HelloWorld")
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addMethod(main)
                .build();

        JavaFile javaFile = JavaFile.builder("com.example.huii.myapplication", helloWorld)
                .build();

        try {
            javaFile.writeTo(processingEnv.getFiler());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

//    private AnnotatedClass getAnnotatedClass(Element element) {
//        TypeElement typeElement = (TypeElement) element.getEnclosingElement();
//        String fullName = typeElement.getQualifiedName().toString();
//        AnnotatedClass annotatedClass = mAnnotatedClassMap.get(fullName);
//        if (annotatedClass == null) {
//            annotatedClass = new AnnotatedClass(typeElement, mElementUtils);
//            mAnnotatedClassMap.put(fullName, annotatedClass);
//        }
//        return annotatedClass;
//    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }
}
