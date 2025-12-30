package com.fit;

import io.micronaut.core.optim.StaticOptimizations;
import io.micronaut.core.util.EnvironmentProperties;
import java.lang.Override;
import java.lang.String;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EnvironmentPropertiesOptimizationLoader implements StaticOptimizations.Loader<EnvironmentProperties> {
  private void load0(Map<String, List<String>> env) {
    env.put("PATH", Arrays.asList("path"));
    env.put("TERM", Arrays.asList("term"));
    env.put("LANG", Arrays.asList("lang"));
    env.put("ANDROID_HOME", Arrays.asList("android.home", "android-home"));
    env.put("NVM_INC", Arrays.asList("nvm.inc", "nvm-inc"));
    env.put("LOGNAME", Arrays.asList("logname"));
    env.put("PWD", Arrays.asList("pwd"));
    env.put("XPC_SERVICE_NAME", Arrays.asList("xpc.service.name", "xpc.service-name", "xpc-service.name", "xpc-service-name"));
    env.put("TERM_PROGRAM_VERSION", Arrays.asList("term.program.version", "term.program-version", "term-program.version", "term-program-version"));
    env.put("__CFBundleIdentifier", Arrays.asList("..cfbundleidentifier", ".-cfbundleidentifier", "-.cfbundleidentifier", "--cfbundleidentifier"));
    env.put("NVM_CD_FLAGS", Arrays.asList("nvm.cd.flags", "nvm.cd-flags", "nvm-cd.flags", "nvm-cd-flags"));
    env.put("NVM_DIR", Arrays.asList("nvm.dir", "nvm-dir"));
    env.put("SHELL", Arrays.asList("shell"));
    env.put("TERM_PROGRAM", Arrays.asList("term.program", "term-program"));
    env.put("PAGER", Arrays.asList("pager"));
    env.put("LSCOLORS", Arrays.asList("lscolors"));
    env.put("USER", Arrays.asList("user"));
    env.put("BUN_INSTALL", Arrays.asList("bun.install", "bun-install"));
    env.put("ZSH", Arrays.asList("zsh"));
    env.put("TMPDIR", Arrays.asList("tmpdir"));
    env.put("SSH_AUTH_SOCK", Arrays.asList("ssh.auth.sock", "ssh.auth-sock", "ssh-auth.sock", "ssh-auth-sock"));
    env.put("XPC_FLAGS", Arrays.asList("xpc.flags", "xpc-flags"));
    env.put("__CF_USER_TEXT_ENCODING", Arrays.asList("..cf.user.text.encoding", "..cf.user.text-encoding", "..cf.user-text.encoding", "..cf.user-text-encoding", "..cf-user.text.encoding", "..cf-user.text-encoding", "..cf-user-text.encoding", "..cf-user-text-encoding", ".-cf.user.text.encoding", ".-cf.user.text-encoding", ".-cf.user-text.encoding", ".-cf.user-text-encoding", ".-cf-user.text.encoding", ".-cf-user.text-encoding", ".-cf-user-text.encoding", ".-cf-user-text-encoding", "-.cf.user.text.encoding", "-.cf.user.text-encoding", "-.cf.user-text.encoding", "-.cf.user-text-encoding", "-.cf-user.text.encoding", "-.cf-user.text-encoding", "-.cf-user-text.encoding", "-.cf-user-text-encoding", "--cf.user.text.encoding", "--cf.user.text-encoding", "--cf.user-text.encoding", "--cf.user-text-encoding", "--cf-user.text.encoding", "--cf-user.text-encoding", "--cf-user-text.encoding", "--cf-user-text-encoding"));
    env.put("LESS", Arrays.asList("less"));
    env.put("LS_COLORS", Arrays.asList("ls.colors", "ls-colors"));
    env.put("NVM_BIN", Arrays.asList("nvm.bin", "nvm-bin"));
    env.put("HOME", Arrays.asList("home"));
    env.put("SHLVL", Arrays.asList("shlvl"));
  }

  @Override
  public EnvironmentProperties load() {
    Map<String, List<String>> env = new HashMap<String, List<String>>();
    load0(env);
    return EnvironmentProperties.of(env);
  }
}
